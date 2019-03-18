package ru.job4j.cinemaarhitecture.fankinterface;

public interface BiConEx<L, R> {
    void accept(L left, R right) throws Exception;
}
