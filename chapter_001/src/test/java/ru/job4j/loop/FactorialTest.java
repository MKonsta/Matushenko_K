package ru.job4j.loop;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void factorialForFive() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
    @Test
    public void factorialForEight() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(8);
        assertThat(result, is(40320));
    }

}
