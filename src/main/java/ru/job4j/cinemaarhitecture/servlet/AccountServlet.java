package ru.job4j.cinemaarhitecture.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.job4j.cinemaarhitecture.dbmanager.Dispatch;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ObjectMapper mapper = new ObjectMapper();
        String temp;
        try {
            if (session.getAttribute("cell") != null) {
                session.setAttribute("account", mapper.readValue(req.getReader().readLine().toString(), Account.class));
                Dispatch.getInstance().access("add", new Ticket((Cell) session.getAttribute("cell"),
                        (Account) session.getAttribute("account")), new Ticket(new Cell(), new Account()));
                System.out.println("билет куплен");
                session.invalidate();
            } else {
                session.invalidate();
                req.getRequestDispatcher("/index.html").forward(req, resp);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
