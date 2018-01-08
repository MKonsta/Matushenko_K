package ru.job4j.array;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void threeSquare() {
        Square a = new Square();
        int[] result = new int[4];
        result = a.calculate(3);
        int[] aser = new int[] {0, 1, 4, 9};
        assertThat(result, is(aser));
    }

    @Test
    public void fourSquare() {
        Square a = new Square();
        int[] result = new int[5];
        result = a.calculate(4);
        int[] aser = new int[] {0, 1, 4, 9, 16};
        assertThat(result, is(aser));
    }
}
