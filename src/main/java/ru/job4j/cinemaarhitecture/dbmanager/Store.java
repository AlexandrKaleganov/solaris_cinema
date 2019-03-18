package ru.job4j.cinemaarhitecture.dbmanager;

import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface Store {
    //метод получает id  нужной позиции
    public Cell getCellID(Cell cell);

    //метод добавления аккаунта
    public Account addAccount(Account account);

    //будет проверять занято место или нет
    public boolean isCheckedCell(Cell cell);

    //метод будет получать список всех занятых клеток
    public List<Cell> getListCell();
    //при добавлении илета в базу будет произведён коммит
    public Ticket addTicket(Ticket ticket);
    public Account getAccount(Account acoun);
    //метод будет делать коммит
    public void commit();
    public void rollback();
}
