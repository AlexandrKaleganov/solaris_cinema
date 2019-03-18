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
        this.ticket = new Ticket(1, 2);
        this.ticket2 = new Ticket(1, 2);
        this.ticket3 = new Ticket(1, 4);

    }

    @Test
    public void toStringg() {
        StringWriter actual = new StringWriter();
        StringWriter expected = new StringWriter();
        actual.write(this.ticket.toString());
        expected.write("Ticket{cellID=1, accounID=2}");
        Assert.assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    public void getAccounID() {
        assertThat(ticket.getAccounID(), is(2));
    }

    @Test
    public void getCellID() {
        assertThat(ticket.getCellID(), is(1));
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