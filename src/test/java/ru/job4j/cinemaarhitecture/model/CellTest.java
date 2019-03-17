package ru.job4j.cinemaarhitecture.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CellTest {
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;

    @Before
    public void resetCell() {
        this.cell1 = new Cell(1, 1, 1);
        this.cell2 = new Cell(1, 1, 1);
        this.cell3 = new Cell(22);

    }

    @Test
    public void getRow() {
        Assert.assertThat(this.cell1.getRow(), is(1));
        Assert.assertThat(this.cell3.getRow(), is(2));

    }

    @Test
    public void getPlace() {
        Assert.assertThat(this.cell1.getPlace(), is(1));
        Assert.assertThat(this.cell3.getPlace(), is(2));


    }

    @Test
    public void getId() {
        Assert.assertThat(this.cell1.getId(), is(1));
        Assert.assertThat(this.cell3.getId(), is((Integer) null));

    }

    @Test
    public void setId() {
        this.cell1.setId(3);
        this.cell3.setId(1);
        Assert.assertThat(this.cell1.getId(), is(3));
        Assert.assertThat(this.cell3.getId(), is(1));

    }

    @Test
    public void setRow() {
        this.cell1.setRow(3);
        Assert.assertThat(this.cell1.getRow(), is(3));

    }

    @Test
    public void setPlace() {
        this.cell1.setPlace(3);
        Assert.assertThat(this.cell1.getPlace(), is(3));
    }

    @Test
    public void equals() {
        Assert.assertThat(this.cell1.equals(this.cell2), is(true));
        Assert.assertThat(this.cell1.equals(this.cell3), is(false));

    }

    @Test
    public void hashCodee() {
        Assert.assertThat(this.cell1.hashCode() == this.cell2.hashCode(), is(true));
        Assert.assertThat(this.cell1.hashCode() == this.cell3.hashCode(), is(false));
    }

    @Test
    public void toStringg() {
        StringWriter actual = new StringWriter();
        StringWriter expected = new StringWriter();
        actual.write(this.cell1.toString());
        expected.write("Cell{id=1, row=1, place=1}");
        Assert.assertThat(actual.toString(), is(expected.toString()));
    }
}