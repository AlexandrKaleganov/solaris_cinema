package ru.job4j.cinemaarhitecture.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountTest {
    private Account account;
    private Account account2;
    private Account account3;


    @Before
    public void setUp() throws Exception {
        this.account = new Account(1, "Sasha", "+79139007067");
        this.account2 = new Account(1, "Vasia", "+79139007067");
        this.account3 = new Account(1, "Sasha", "+79139007067");
    }

    @Test
    public void equals() {
        assertThat(account.equals(account3), is(true));
        assertThat(account.equals(account2), is(false));

    }

    @Test
    public void toStringg() {
        StringWriter actual = new StringWriter();
        StringWriter expected = new StringWriter();
        actual.write(this.account.toString());
        expected.write("Account{id=1, name='Sasha', tel='+79139007067'}");
        Assert.assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    public void hashCodee() {
        assertThat(account.hashCode() == account2.hashCode(), is(true));
        assertThat(account.hashCode() == account3.hashCode(), is(true));

    }

    @Test
    public void getId() {
        assertThat(account.getId(), is(1));
    }

    @Test
    public void setId() {
        account2.setId(8);
        assertThat(account2.getId(), is(8));
    }

    @Test
    public void getName() {
        assertThat(account.getName(), is("Sasha"));
    }

    @Test
    public void setName() {
        account.setName("vasia");
        assertThat(account.getName(), is("vasia"));
    }

    @Test
    public void getTel() {
        assertThat(account.getTel(), is("+79139007067"));
    }

    @Test
    public void setTel() {
        account.setTel("123");
        assertThat(account.getTel(), is("123"));
    }
}