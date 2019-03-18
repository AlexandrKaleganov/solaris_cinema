package ru.job4j.cinemaarhitecture.model;

import java.util.Objects;

/**
 * модель билет
 */
public class Ticket {
    private final Integer cellID;
    private final Integer accounID;

    @Override
    public String toString() {
        return "Ticket{" + "cellID=" + cellID + ", accounID=" + accounID + '}';
    }

    public Ticket(Integer cellID, Integer accounID) {
        this.accounID = accounID;
        this.cellID = cellID;
    }

    public Integer getAccounID() {
        return accounID;
    }

    public Integer getCellID() {
        return cellID;
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
        return Objects.equals(accounID, ticket.accounID) && Objects.equals(cellID, ticket.cellID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounID, cellID);
    }
}
