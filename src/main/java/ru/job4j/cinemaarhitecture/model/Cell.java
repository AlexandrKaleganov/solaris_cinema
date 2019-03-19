package ru.job4j.cinemaarhitecture.model;

import java.util.Objects;

/**
 * класс адрес места, будет
 * получать десятичное число первое - будет ряд
 * второе - место
 */
public class Cell {
    private Integer id;
    private Integer row;
    private Integer place;

    public Cell(Integer cell) {
        this.row = cell / 10;
        this.place = cell % 10;
    }

    public Cell(Integer id, Integer row, Integer place) {
        this.id = id;
        this.row = row;
        this.place = place;
    }

    public Cell() {
    }

    public Integer getRow() {
        return row;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(id, cell.id) && Objects.equals(row, cell.row) && Objects.equals(place, cell.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, place);
    }

    @Override
    public String toString() {
        return "Cell{" + "id=" + id + ", row=" + row + ", place=" + place + '}';
    }
}
