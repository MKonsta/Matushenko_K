package ru.job4j.chessboardframe;

public abstract class Figure {
    public final Cell position;

     Figure(Cell position) {
        this.position = position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);
}
