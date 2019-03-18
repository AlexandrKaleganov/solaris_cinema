package ru.job4j.cinemaarhitecture.model;

import java.util.Objects;

/**
 * модель билет
 */
public class Ticket {
    private final Cell cell;
    private final Account accoun;

    @Override
    public String toString() {
        return "Ticket{" + "cellID=" + cell + ", accounID=" + accoun + '}';
    }

    public Ticket(Cell cellID, Account accounID) {
        this.accoun = accounID;
        this.cell = cellID;
    }

    public Account getAccoun() {
        return accoun;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(accoun, ticket.accoun) && Objects.equals(cell, ticket.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accoun, cell);
    }
}
