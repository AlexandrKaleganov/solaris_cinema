package ru.job4j.cinemaarhitecture.dbmanager;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.fankinterface.BiFunEx;
import ru.job4j.cinemaarhitecture.fankinterface.FunEx;
import ru.job4j.cinemaarhitecture.model.Ticket;

public class Dispatch {
    /**
     * Dispatch.
     */

    private final Map<String, BiFunEx<Store, Ticket, Optional>> dispatch = new HashMap<String, BiFunEx<Store, Ticket, Optional>>();

    private final static Dispatch INSTANCE = new Dispatch().init();
    private static final Logger LOGGER = Logger.getLogger(Dispatch.class);

    public static Dispatch getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial handlers.
     *
     * @return current object.
     */
    public Dispatch init() {
        this.dispatch.put("add", (db, ticket) ->
                Optional.of(db.addTicket(ticket)));
        this.dispatch.put("getListHall", (db, ticket) ->
                Optional.of(db.getListCell()));
        return this;
    }

    /**
     * параметр указывает на то , какой тип данных мы получи
     *
     * @param key
     * @param ticket
     * @param param
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> E access(String key, Ticket ticket, E param) throws Exception {
        Optional<E> rsl = Optional.empty();
        try (Store dbstore = new DbStore()) {
            rsl = this.dispatch.get(key).apply(dbstore, ticket);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Err("БИлет не куплен");
        }
        return rsl.get();
    }
}