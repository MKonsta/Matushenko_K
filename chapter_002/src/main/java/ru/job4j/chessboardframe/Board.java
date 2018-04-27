package ru.job4j.chessboardframe;

public class Board {
    Figure[] figures = new Figure[32];
    private int i = 0;
    public void add(Figure figure) {
        figures[i] = figure;
        i++;
    }

    public boolean move(Cell sourse, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean res = true;
        if (!exist(sourse)) {
            throw new FigureNotFoundException();
        }
        for (Cell cell : figures[indexOf(sourse)].way(sourse, dest)) {
            for (Figure figure : figures) {
                if (figure != null && figure.position.getX() == cell.getX() && figure.position.getY() == cell.getY()) {
                    res = false;
                    throw new OccupiedWayException();
                }
            }
        }
        if (res) {
            figures[indexOf(sourse)] = figures[indexOf(sourse)].copy(dest); //тут ошибка java.lang.ArrayIndexOutOfBoundsException: -1
            //System.out.println(indexOf(sourse));
        }
        return res;
    }

    public boolean exist(Cell sourse) {
        boolean res = false;
        for (Figure figure : figures) {
            if (figure != null && figure.position.getX() == sourse.getX() && figure.position.getY() == sourse.getY()) {
                res = true;
            }
        }
        return res;
    }

    public int indexOf(Cell cell) {
        int index = -1;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null && figures[i].position.getX() == cell.getX() && figures[i].position.getY() == cell.getY()) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();

        board.add(new Bishop(new Cell(1, 6)));
        board.add(new Bishop(new Cell(3, 4)));
        board.add(new Bishop(new Cell(3, 1)));
        board.move(new Cell(3, 4), new Cell(6, 7));

        for (Figure figure : board.figures) {
            if (figure != null) {
                System.out.println(board.indexOf(figure.position) + "  " + figure.position.getX() + " " + figure.position.getY());
            }
        }


    }

}
