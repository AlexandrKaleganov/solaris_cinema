package ru.job4j.cinemaarhitecture.servlet;

import org.apache.log4j.Logger;
import ru.job4j.cinemaarhitecture.dbmanager.Dispatch;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ClearServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Dispatch.getInstance().access("clear", new Ticket(new Cell(), new Account()), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
