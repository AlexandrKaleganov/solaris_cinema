package ru.job4j.cinemaarhitecture.dbmanager;

import org.apache.log4j.Logger;
import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.fankinterface.BiConEx;
import ru.job4j.cinemaarhitecture.fankinterface.FunEx;
import ru.job4j.cinemaarhitecture.fankinterface.TriplexConEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DbStore implements Store {
    private final Map<Class<?>, TriplexConEx<Integer, PreparedStatement, Object>> dispat = new HashMap<>();
    private HashMap<String, ArrayList<String>> map;
    private static final Logger LOGGER = Logger.getLogger(DbStore.class);
    private Connection conn;
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        this.init();
        this.dispat.put(Integer.class, (index, ps, value) -> ps.setInt(index, (Integer) value));
        this.dispat.put(String.class, (index, ps, value) -> ps.setString(index, (String) value));
    }

    public static DbStore getStore() {
        return INSTANCE;
    }

    private void init() {
        try {
            Properties settings = new Properties();
            try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("gradle.properties")) {
                settings.load(in);
            }
            Class.forName(settings.getProperty("db.driver"));
            this.conn = DriverManager.getConnection(settings.getProperty("db.host"),
                    settings.getProperty("db.login"), settings.getProperty("db.password"));
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer updateInfo(String command, List<Object> att) throws Exception {
        return this.db(command, att, ps -> {
            Integer k = 0;
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                while (resultSet.next()) {
                    k = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                conn.rollback();
            }
            return k;
        }).get();
    }

    private void updateInfo(String command) throws Exception {
        this.db(command, new ArrayList<>(), ps ->
                ps.executeUpdate()
        );
    }

    /**
     * метод получит индекс если объект будет найден,
     * если не найдет то индекс будет равен 0
     *
     * @param command
     * @param att
     * @return
     */
    private Integer isIndex(String command, List<Object> att) throws Exception {
        return this.db(command, att, ps -> {
            Integer k = 0;
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    k = resultSet.getInt("id");
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return k;
        }).get();
    }

    /**
     * рефакторинг для добавления объектов в бд
     *
     * @param sql
     * @param param
     * @param fun
     * @param <R>
     * @return
     */

    private <R> Optional<R> db(String sql, List<Object> param, FunEx<PreparedStatement, R> fun) throws Exception {
        Optional<R> rsl = Optional.empty();
        try (PreparedStatement pr = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            this.forIdex(param, (index, value) -> dispat.get(value.getClass()).accept(index + 1, pr, value));
            rsl = Optional.ofNullable(fun.apply(pr));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            try {
                conn.rollback();
                throw new SQLException();
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage());
            }
        }
        return rsl;
    }

    private <T> void forIdex(List<T> list, BiConEx<Integer, T> metod) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            metod.accept(i, list.get(i));
        }
    }

    public void deleteAllInfo() {
        try {
            this.updateInfo("delete from purchased_seats");
            this.updateInfo("delete from accounts");
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * метод будет возвращать нам id  необходимой клетки(необходимого места)
     *
     * @param cell
     * @return
     */
    @Override
    public Cell getCellID(Cell cell) throws Exception {
        cell.setId(this.isIndex("select * from halls where row = ? and  place = ?",
                Arrays.asList(cell.getRow(), cell.getPlace())));
        return cell;
    }

    /**
     * преоверяте есть ли пользователь в базе и если нету то добавляет,
     * если есть то получает пользователя с id
     *
     * @param account
     * @return
     */
    @Override
    public Account addAccount(Account account) throws Exception {
        account.setId(this.isIndex("select * from accounts where name=? and telephone = ?",
                Arrays.asList(account.getName(), account.getTel())));
        if (account.getId() == 0) {
            account.setId(this.updateInfo("insert into accounts(name, telephone) values (?,?)",
                    Arrays.asList(account.getName(), account.getTel())));
        }
        return account;
    }

    /**
     * если место в зале занято то вернёт true
     *
     * @param cell
     * @return
     */
    @Override
    public boolean isCheckedCell(Cell cell) throws Exception {
        boolean rsl = false;
        Integer test = this.isIndex("select * from purchased_seats where halls_id = ?",
                Arrays.asList(this.getCellID(cell).getId()));
        if (test > 0) {
            rsl = true;
        }
        return rsl;
    }

    /**
     * получаем список занятых клеток
     *
     * @return
     */
    @Override
    public ArrayList<Cell> getListCell() throws Exception {
        return this.db("select * from halls where id in (select halls_id from purchased_seats)", new ArrayList<>(), ps -> {
            ArrayList<Cell> rsl = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rsl.add(new Cell(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
                }

            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return rsl;
        }).get();
    }

    /**
     * в случае возникновения ошибки будет происходить откат действий
     *
     * @param ticket
     * @return
     * @throws Err
     */
    @Override
    public Ticket addTicket(Ticket ticket) throws Err {
        try {
            ticket.getCell().setId(this.getCellID(ticket.getCell()).getId());
            ticket.getAccoun().setId(this.addAccount(ticket.getAccoun()).getId());
            this.updateInfo("insert into purchased_seats(accounts_id, halls_id) values(?, ?)",
                    Arrays.asList(ticket.getAccoun().getId(), ticket.getCell().getId()));
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
                LOGGER.error(e.getMessage(), e);
                throw new Err("место уже занято");
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
                throw new Err("Ща воообще что-то не понятное произошло");
            }
        }
        return ticket;
    }

    /**
     * проверка добавлен пользователь или нет в БД
     *
     * @param acoun
     * @return
     */
    @Override
    public Account getAccount(Account acoun) throws Exception {
        acoun.setId(this.isIndex("select * from accounts where name = ? and telephone = ?",
                Arrays.asList(acoun.getName(), acoun.getTel())));
        return acoun;
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }

}
