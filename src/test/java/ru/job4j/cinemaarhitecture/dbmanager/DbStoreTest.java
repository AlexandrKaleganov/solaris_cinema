package ru.job4j.cinemaarhitecture.dbmanager;

import org.junit.Test;
import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.fankinterface.BiConEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DbStoreTest {
    private DbStore dbStore;

    private void fullTest(BiConEx<DbStore, Account> fank) throws Exception {
        Account users = new Account(0, "Sasha", "123");
        DbStore dbStore = new DbStore();
        try {
            Account expected = dbStore.addAccount(users);
            fank.accept(dbStore, expected);
        } finally {
            dbStore.deleteAllInfo();
            dbStore.close();
        }
    }

    @Test
    public void getCellID() throws Exception {
        this.fullTest((db, ac) -> {
            assertThat(db.getCellID(new Cell(11)), is(new Cell(1, 1, 1)));
        });
    }

    @Test
    public void addAccount() throws Exception {
        this.fullTest((db, account) -> {
            assertThat(db.getAccount(new Account(1, account.getName(), account.getTel())), is(account));
        });
    }

    @Test
    public void isCheckedCell() throws Exception {
        this.fullTest((db, account) -> {
            db.addTicket(new Ticket(new Cell(0, 1, 1), account));
            assertThat(db.isCheckedCell(new Cell(0, 1, 1)), is(true));
        });
    }

    @Test
    public void getListCell() throws Exception {
        this.fullTest((db, account) -> {
            db.addTicket(new Ticket(new Cell(0, 1, 1), account));
            db.addTicket(new Ticket(new Cell(0, 2, 1), account));
            db.addTicket(new Ticket(new Cell(0, 1, 3), account));
            assertThat(db.getListCell().size(), is(3));
        });
    }

    @Test(expected = Err.class)
    public void testERROR() throws Exception {
        this.fullTest((db, account) -> {
            db.addTicket(new Ticket(new Cell(0, 1, 1), account));
            db.addTicket(new Ticket(new Cell(0, 1, 1), account));
            assertThat(db.getListCell().size(), is(1));
        });
    }

    @Test
    public void addTicket() throws Exception {
        this.fullTest((db, account) -> {
            db.addTicket(new Ticket(new Cell(0, 1, 1), account));
        });
    }
}