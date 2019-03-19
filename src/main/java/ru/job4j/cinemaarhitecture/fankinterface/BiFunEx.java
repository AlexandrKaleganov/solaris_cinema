package ru.job4j.cinemaarhitecture.fankinterface;

public interface BiFunEx<R, L, E> {
    E apply(R r, L l) throws Exception;
}
