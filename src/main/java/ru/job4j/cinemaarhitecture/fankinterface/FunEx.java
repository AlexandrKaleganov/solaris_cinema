package ru.job4j.cinemaarhitecture.fankinterface;

public interface FunEx<E, R> {

    R apply(E e) throws Exception;
}
