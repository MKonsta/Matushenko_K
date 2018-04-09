package ru.job4j.chessboardframe;

public class Bishop extends Figure {
    Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.getX() - source.getX()) != Math.abs(dest.getY() - source.getY())) {
            throw new ImpossibleMoveException("Bishop cannot move there");
        }
        Cell[] road = new Cell[Math.abs(source.getX() - dest.getX())];
        if (source.getX() < dest.getX() && source.getY() < dest.getY()) {
            for (int i = 0; i < road.length; i++) {
                road[i] = new Cell(source.getX() + 1, source.getY() + 1);
                source.setX(source.getX()+1);
                source.setY(source.getY()+1);
            }
        }
        else if (source.getX() < dest.getX() && source.getY() > dest.getY()) {
            for (int i = 0; i < road.length; i++) {
                road[i] = new Cell(source.getX() + 1, source.getY() - 1);
                source.setX(source.getX() + 1);
                source.setY(source.getY() - 1);
            }
        }
        else if (source.getX() > dest.getX() && source.getY() > dest.getY()) {
            for (int i = 0; i < road.length; i++) {
                road[i] = new Cell(source.getX() - 1, source.getY() - 1);
                source.setX(source.getX() - 1);
                source.setY(source.getY() - 1);
            }
        }
        else if (source.getX() > dest.getX() && source.getY() < dest.getY()) {
            for (int i = 0; i < road.length; i++) {
                road[i] = new Cell(source.getX() - 1, source.getY() + 1);
                source.setX(source.getX() - 1);
                source.setY(source.getY() + 1);
            }
        }
        return road;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

    public static void main(String[] args) throws ImpossibleMoveException {
        Bishop bishop = new Bishop(new Cell(3, 4));
        for (Cell e : bishop.way(bishop.position, new Cell(7, 8))) {
            System.out.println("X: " + e.getX() + "      " + "Y: " + e.getY());
        }

    }
}