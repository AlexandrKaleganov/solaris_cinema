package ru.job4j.cinemaarhitecture.dbmanager;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.fankinterface.BiConEx;
import ru.job4j.cinemaarhitecture.fankinterface.BiFunEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateTest {
    private Valid valid;
    private Ticket ticket;

    @Before
    public void init() throws Exception {
        this.valid = Validate.getINSTACCE();
        Cell cell = new Cell(1, 1, 1);
        Account account = new Account(1, "misha", "8913");
        ticket = new Ticket(cell, account);
        valid.addTicket(ticket);
    }

    public <E> void fulltest(BiConEx<Valid, Ticket> fank) throws Exception {
        try {
            fank.accept(valid, ticket);
        } finally {
            this.valid.deleteAll();
        }
    }

    @Test
    public void isCheckedCell() throws Exception {
        this.fulltest((valid, ticket) -> {
            assertThat(valid.isCheckedCell(ticket.getCell()), is(true));
            assertThat(valid.isCheckedCell(new Cell(1, 2, 2)), is(false));

        });
    }

    @Test
    public void getListCell() throws Exception {
        this.fulltest((valid, ticket) -> {
            assertThat(valid.getListCell().size(), is(1));
            assertThat(valid.getListCell().get(0), is(new Cell(1, 1, 1)));
        });
    }

    @Test(expected = Err.class)
    public void addTicket() throws Exception {
        this.fulltest((valid, ticket) -> {
            valid.addTicket(ticket);
        });
    }
}