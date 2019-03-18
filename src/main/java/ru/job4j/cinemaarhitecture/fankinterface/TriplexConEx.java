package ru.job4j.cinemaarhitecture.fankinterface;

public interface TriplexConEx<E, R, K> {
    void accept(E e, R r, K k) throws Exception;
}
