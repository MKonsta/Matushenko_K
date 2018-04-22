package ru.job4j;

import org.junit.Test;
import ru.job4j.chessboardframe.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTests {
    @Test
    public void findIndexOfFigureInArray() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 6)));
        board.add(new Bishop(new Cell(1, 2)));
        board.add(new Bishop(new Cell(3, 4)));
        board.add(new Bishop(new Cell(5, 2)));
        assertThat(board.indexOf(new Cell(3, 4)), is(2));
        assertThat(board.indexOf(new Cell(1, 6)), is(0));
    }

    @Test
    public void checkFigureExisting() {
        Board board = new Board();
        board.add(new Bishop(new Cell(6, 7)));
        board.add(new Bishop(new Cell(1, 2)));
        assertThat(board.exist(new Cell(1, 2)), is(true));
        assertThat(board.exist(new Cell(6, 7)), is(true));
        assertThat(board.exist(new Cell(4, 6)), is(false));
    }

    @Test
    public void whenReturnFigureWay() throws ImpossibleMoveException {
        Bishop bishop = new Bishop(new Cell(3, 4));
        Cell[] expect = {new Cell(4, 3), new Cell(5, 2), new Cell(6, 1)};
        assertThat(bishop.way(new Cell(3, 4), new Cell(6, 1)), is(expect));
    }

    @Test
    public void whenBishopMoveFrom34To61() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 4)));
        board.move(new Cell(3,4), new Cell(6, 1));
        assertThat(board.exist(new Cell(6, 1)), is(true));
    }
}
