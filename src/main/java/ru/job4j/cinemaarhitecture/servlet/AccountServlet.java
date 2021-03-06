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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * сервлет обслуживает запросы, которые приходят с  paymentscript.js
 * гет запрос получает данные из сессии решин не делать лишние запросы в бд, хотя всё можно прикрутить к примеру если стоимость билета
 * на место разная , указывать цену за билет
 * и подгружать стоимость белета
 *
 * пост запрос добавлеет данные в базу если выскакивает исключение то формируется респонс в формате JSON
 * и сессия убивается в любом случае в блоке finally
 */
public class AccountServlet extends HttpServlet {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=utf-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper maper = new ObjectMapper();
        writer.append(maper.writeValueAsString(req.getSession().getAttribute("cell")));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            if (session.getAttribute("cell") != null) {
                session.setAttribute("account", mapper.readValue(req.getReader().readLine(), Account.class));
                Dispatch.getInstance().access("add", new Ticket((Cell) session.getAttribute("cell"),
                        (Account) session.getAttribute("account")), new Ticket(new Cell(), new Account()));
            }
        } catch (Exception e) {
            resp.setContentType("text/json; charset=utf-8");
            writer.append(mapper.writeValueAsString(req.getSession().getAttribute("cell")));
            writer.flush();
            LOGGER.info(e.getMessage(), e);
        } finally {
            session.invalidate();
        }
    }
}
