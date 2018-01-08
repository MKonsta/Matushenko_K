package ru.job4j.array;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void findForFive() {
        FindLoop fi = new FindLoop();
        int[] ar = {4, 7, 8, 5};
        int result = fi.indexOf(ar, 5);
        assertThat(result, is(3));
    }
    @Test
    public void notFind() {
        FindLoop fi = new FindLoop();
        int[] ar = {4, 7, 8, 5};
        int result = fi.indexOf(ar, 70);
        assertThat(result, is(-1));
    }

}
