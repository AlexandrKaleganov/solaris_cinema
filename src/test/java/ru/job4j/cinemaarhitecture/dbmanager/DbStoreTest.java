package ru.job4j.cinemaarhitecture.dbmanager;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cinemaarhitecture.fankinterface.BiConEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DbStoreTest {
    private DbStore dbStore;

    private void fullTest(BiConEx<DbStore, Account> fank) {
        Account users = new Account(0, "Sasha", "123");
        DbStore dbStore = new DbStore();
        Account expected = dbStore.addAccount(users);
        try {
            fank.accept(dbStore, expected);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbStore.deleteAllInfo();
        }
    }


    @Test
    public void getCellID() {
        this.fullTest((db, ac) -> {
            assertThat(db.getCellID(new Cell(11)), is(new Cell(1, 1, 1)));
        });
    }

    @Test
    public void addAccount() {
        this.fullTest((db, account) -> {
            db.commit();
            assertThat(db.getAccount(new Account(1, account.getName(), account.getTel())), is(account));
        });
    }

    @Test
    public void isCheckedCell() {
    }

    @Test
    public void getListCell() {
    }

    @Test
    public void addTicket() {
    }
}