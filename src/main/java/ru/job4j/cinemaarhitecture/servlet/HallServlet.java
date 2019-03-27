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
import java.io.PrintWriter;
import java.util.ArrayList;

public class HallServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(HallServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=utf-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Cell> list = Dispatch.getInstance().access(req.getParameter("action"),
                    new Ticket(new Cell(), new Account()), new ArrayList<Cell>());
            writer.append(mapper.writeValueAsString(list));
            writer.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cell cel = mapper.readValue(req.getReader().readLine(), Cell.class);
            session.setAttribute("cell", cel);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
