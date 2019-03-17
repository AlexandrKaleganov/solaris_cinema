package ru.job4j.cinemaarhitecture.dbmanager;

public interface Store {


    //метод получает id  нужной позиции
    public Integer getCellID();
    //будет проверять занято место или нет
    public boolean isCheckedCell();
}
