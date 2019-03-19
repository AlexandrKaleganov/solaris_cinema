package ru.job4j.cinemaarhitecture.dbmanager;

import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.util.List;

public interface Valid {

    //будет проверять занято место или нет
    public boolean isCheckedCell(Cell cell) throws Exception;

    //метод будет получать список всех занятых клеток
    public List<Cell> getListCell() throws Exception;

    //при добавлении илета в базу будет произведён коммит
    //наша транзакция
    public Ticket addTicket(Ticket ticket) throws Exception;

    public void deleteAll() throws Exception;
}
