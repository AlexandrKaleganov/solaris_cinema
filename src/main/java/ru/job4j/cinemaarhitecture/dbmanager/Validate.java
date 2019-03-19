package ru.job4j.cinemaarhitecture.dbmanager;

import org.apache.log4j.Logger;
import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.fankinterface.BiFunEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.util.List;
import java.util.Optional;

public class Validate implements Valid {
    private static final Logger LOGGER = Logger.getLogger(Dispatch.class);


    private static final Valid INSTACCE = new Validate();

    public static Valid getINSTACCE() {
        return INSTACCE;
    }

    @Override
    public boolean isCheckedCell(Cell cell) throws Exception {
        return this.access(new Ticket(cell, new Account()), (db, tisket) ->
                db.isCheckedCell(tisket.getCell())
        );
    }


    @Override
    public List<Cell> getListCell() throws Exception {
        return this.access(new Ticket(new Cell(), new Account()), (db, tisket) ->
                db.getListCell()
        );
    }

    @Override
    public Ticket addTicket(Ticket ticket) throws Exception {
        return this.access(ticket, (db, tisket) ->
                db.addTicket(ticket)
        );
    }

    @Override
    public void deleteAll() throws Exception {
        this.access(new Ticket(new Cell(), new Account()), (db, tisket) -> {
                    db.deleteAllInfo();
                    return tisket;
                }
        );
    }

    private <E> E access(Ticket ticket, BiFunEx<Store, Ticket, E> fank) throws Exception {
        Optional<E> rsl = Optional.empty();
        try (Store dbstore = new DbStore()) {
            rsl = Optional.of(fank.apply(dbstore, ticket));
        } catch (Exception e) {
            throw new Err("Не удалось купить билот, возможно место уже занято");
        }
        return rsl.get();
    }
}
