package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UpArrayTest {
    @Test
    public void testOne() {
        int[] a = {5, 10, 15, 20, 50,};
        int[] b = {6, 7,  8,  18, 19, 23, 48, 63,};
        UpArray upArray = new UpArray();
        int[] result = upArray.ar(a, b);
        int[] expect = {5, 6, 7, 8, 10, 15, 18, 19, 20, 23, 48, 50, 63};
        assertThat(expect, is(result));
    }

    @Test
    public void testTwo() {
        int[] a = {54, 68, 73, 80};
        int[] b = {30, 35, 55};
        UpArray upArray = new UpArray();
        int[] result = upArray.ar(a, b);
        int[] expect = {30, 35, 54, 55, 68, 73, 80};
        assertThat(expect, is(result));
    }
}
