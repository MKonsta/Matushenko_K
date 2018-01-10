package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void whenMultipleFour() {
        Matrix mul = new Matrix();
        int[][] expect = {{1, 2, 3, 4}, {2, 4, 6, 8}, {3, 6, 9, 12}, {4, 8, 12, 16}};
        int[][] result = mul.multiplie(4);
        assertThat(result, is(expect));
    }

}
