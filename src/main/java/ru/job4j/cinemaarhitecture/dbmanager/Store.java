package ru.job4j.cinemaarhitecture.dbmanager;

import ru.job4j.cinemaarhitecture.model.Cell;

import java.util.ArrayList;

public interface Store {
    //метод получает id  нужной позиции
    public Integer getCellIDc(Cell cell);

    //будет проверять занято место или нет
    public boolean isCheckedCell(Cell cell);

    //метод будет получать список всех занятых клеток
    public ArrayList<Cell> getListCell();
}
