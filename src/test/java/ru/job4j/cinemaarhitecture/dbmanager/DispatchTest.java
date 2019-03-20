package ru.job4j.cinemaarhitecture.dbmanager;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cinemaarhitecture.fankinterface.BiConEx;
import ru.job4j.cinemaarhitecture.fankinterface.BiFunEx;
import ru.job4j.cinemaarhitecture.fankinterface.FunEx;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DispatchTest {
    private Dispatch dispatch;
    private Ticket ticket;

    @Before
    public void init() throws Exception {
        this.dispatch = Dispatch.getInstance();
        this.ticket = new Ticket(new Cell(1, 1, 1), new Account(1, "vasia", "123"));
        ticket = dispatch.access("add", ticket, new Ticket(new Cell(), new Account()));
    }

    private void fulltest(BiConEx<Dispatch, Ticket> fank) throws Exception {
        try {
            fank.accept(this.dispatch, this.ticket);
        } finally {
            try (DbStore store = new DbStore()) {
                store.deleteAllInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAdd() throws Exception {
        this.fulltest((dispatch, ticket) -> {
            assertThat(dispatch.access("getListHall", ticket, new ArrayList<Cell>()).get(0), is(new Cell(1, 1, 1)));
        });
    }
}