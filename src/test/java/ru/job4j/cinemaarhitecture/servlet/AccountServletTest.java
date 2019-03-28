package ru.job4j.cinemaarhitecture.servlet;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cinemaarhitecture.dbmanager.DbStore;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class AccountServletTest {
    private AccountServlet servlet;
    private HttpSession session;
    private HttpServletRequest req;
    private HttpServletResponse res;
    private StringReader acc;
    private ServletOutputStream strim;

    @Before
    public void initparam() {
        this.session = mock(HttpSession.class);
        this.req = mock(HttpServletRequest.class);
        this.res = mock(HttpServletResponse.class);
        this.acc = new StringReader("{\"id\":\"0\",\"name\":\"Dsdfds Dsadas Dasda\",\"tel\":\"+79139007067\"}");
        this.strim = mock(ServletOutputStream.class);
        this.servlet = new AccountServlet();
    }

    @Test
    public void doPost() throws Exception {
        when(this.req.getSession()).thenReturn(session);
        when(this.session.getAttribute("cell")).thenReturn(new Cell(0, 1, 1));
        when(this.session.getAttribute("account")).thenReturn(new Account(1, "Вася", "8913"));
        when(this.req.getReader()).thenReturn(new BufferedReader(acc));
        when(this.res.getOutputStream()).thenReturn(this.strim);
        this.servlet.doPost(this.req, this.res);
        assertThat(DbStore.getStore().getListCell().get(0).getPlace(), is(1));
        DbStore.getStore().deleteAllInfo();
    }
}