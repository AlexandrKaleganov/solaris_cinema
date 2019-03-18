package ru.job4j.cinemaarhitecture.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    private Ticket ticket2;
    private Ticket ticket3;


    @Before
    public void singUp() {
        this.ticket = new Ticket(new Cell(1, 1, 1), new Account(2, "name", "tell"));
        this.ticket2 = new Ticket(new Cell(1, 1, 1), new Account(2, "name", "tell"));
        this.ticket3 = new Ticket(new Cell(2, 1, 1), new Account(2, "name", "tell"));

    }

    @Test
    public void toStringg() {
        StringWriter actual = new StringWriter();
        StringWriter expected = new StringWriter();
        actual.write(this.ticket.toString());
        expected.write("Ticket{cellID=Cell{id=1, row=1, place=1}, accounID=Account{id=2, name='name', tel='tell'}}");
        Assert.assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    public void getAccounID() {
        assertThat(ticket.getAccoun().getId(), is(2));
    }

    @Test
    public void getCellID() {
        assertThat(ticket.getCell().getId(), is(1));
    }

    @Test
    public void equalse() {
        assertThat(ticket.equals(ticket2), is(true));
        assertThat(ticket.equals(ticket3), is(false));
    }

    @Test
    public void hashCodee() {
        assertThat(ticket.hashCode() == ticket2.hashCode(), is(true));
        assertThat(ticket.hashCode() == ticket3.hashCode(), is(false));
    }
}