package ru.job4j.cinemaarhitecture.model;

/**
 * класс адрес места, будет
 * получать десятичное число первое - будет ряд
 * второе - место
 */
public class Cell {
    private final Integer row;
    private final Integer place;

    public Cell(Integer cell) {
        this.row = cell / 10;
        this.place = cell % 10;
    }

    public Cell(Integer row, Integer place) {
        this.row = row;
        this.place = place;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getPlace() {
        return place;
    }

}
