package ru.job4j.chessboardframe;

public class Board {
    Figure[] figures = new Figure[32];
    private int i = 0;
    public void add(Figure figure) {
        figures[i] = figure;
        i++;
    }

    boolean move(Cell sourse, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean res = true;
        if (!exist(sourse)) {
            throw new FigureNotFoundException();
        }
        int index = 0;
        while (figures[index].position.getX() != sourse.getX() && figures[index].position.getY() != sourse.getY()){
            index++;
        }


        for (Cell cell : figures[index].way(sourse, dest)) {
            int i = 0;
            while (figures[i] != null) {
                //System.out.println(cell.getX()+ " " + cell.getY() + " к.фигуры " + figures[i].position.getX() + " " + figures[i].position.getY());
                if (cell.getX() == figures[i].position.getX() && cell.getY() == figures[i].position.getY()) {
                    res = false;
                    throw new OccupiedWayException();
                }
                i++;
            }
        }
        if (res) {
            figures[index] = figures[index].copy(dest);

        }

        return res;
    }

    boolean exist(Cell sourse) {
        boolean res = false;
        for (Figure figure : figures) {
            if (figure != null && figure.position.getX() == sourse.getX() && figure.position.getY() == sourse.getY()) {
                res = true;
            }
        }
        return res;
    }


    public static void main(String[] args) throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();

        board.add(new Bishop(new Cell(1, 6)));
        board.add(new Bishop(new Cell(3, 4)));
        board.add(new Bishop(new Cell(3, 1)));
        board.move(new Cell(3, 1), new Cell(7,5));
        int i = 0;
        while (board.figures[i] != null) {
            System.out.println(board.figures[i].position.getX() + "  " + board.figures[i].position.getY());
            i++;
        }


    }

}
