package ru.job4j.cinemaarhitecture.dbmanager;

import ru.job4j.cinemaarhitecture.error.Err;
import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;
import ru.job4j.cinemaarhitecture.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface Store extends AutoCloseable {
    //метод получает id  нужной позиции
    public Cell getCellID(Cell cell) throws Exception;
    //метод добавления аккаунта
    public Account addAccount(Account account) throws Exception;
    //будет проверять занято место или нет
    public boolean isCheckedCell(Cell cell) throws Exception;
    //метод будет получать список всех занятых клеток
    public List<Cell> getListCell() throws Exception;
    //при добавлении илета в базу будет произведён коммит
    //наша транзакция
    public Ticket addTicket(Ticket ticket) throws Err;
    public Account getAccount(Account acoun) throws Exception;
    public void deleteAllInfo();
}
