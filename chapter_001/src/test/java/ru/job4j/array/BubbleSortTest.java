package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenFiveElements() {
        BubbleSort arr = new BubbleSort();
        int[] rt = {1, 8, 2, 12, 3};
        int[] expect = {1, 2, 3, 8, 12};
        int[] result = arr.sort(rt);
        assertThat(result, is(expect));
    }
}
