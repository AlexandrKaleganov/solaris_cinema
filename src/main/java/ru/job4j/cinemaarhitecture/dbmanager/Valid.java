package ru.job4j.cinemaarhitecture.dbmanager;

import ru.job4j.cinemaarhitecture.model.Account;
import ru.job4j.cinemaarhitecture.model.Cell;

import java.util.ArrayList;
import java.util.List;

public interface Valid {
    //метод получает id  нужной позиции
    public Cell getCellID(Cell cell);

    //метод добавления аккаунта
    public Account addAccount(Account account);

    //метод будет получать список всех занятых клеток
    public List<Cell> getListCell();

    //метод будет делать коммит
    public void commit();

    public void rollback();
}
