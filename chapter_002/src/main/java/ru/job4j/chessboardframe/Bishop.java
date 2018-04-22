package ru.job4j.chessboardframe;

public class Bishop extends Figure {
    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.getX() - source.getX()) != Math.abs(dest.getY() - source.getY())) {
            throw new ImpossibleMoveException("Bishop cannot move there");
        }
        Cell[] road = new Cell[Math.abs(source.getX() - dest.getX())];
//        int deltaX = dest.getX() - source.getX();
//        int deltaY = dest.getY() - source.getY();
        for (int i = 0; i < road.length; i++) {
            road[i] = new Cell(source.getX() + Integer.compare(dest.getX(), source.getX()), source.getY() + Integer.compare(dest.getY(), source.getY()));
            source.setX(source.getX() + Integer.compare(dest.getX(), source.getX()));
            source.setY(source.getY() + Integer.compare(dest.getY(), source.getY()));
        }
        return road;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

    public static void main(String[] args) throws ImpossibleMoveException {
        Bishop bishop = new Bishop(new Cell(3, 4));
        for (Cell e : bishop.way(new Cell(3, 4), new Cell(6, 1))) {
            System.out.println("X: " + e.getX() + "      " + "Y: " + e.getY());
        }
    }
}