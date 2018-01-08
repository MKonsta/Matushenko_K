package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenEven() {
        Turn turn = new Turn();
        int[] ar = {56, 77, 3, 9};
        int[] result = turn.back(ar);
        int[] ra = {9, 3, 77, 56};
        assertThat(result, is(ra));
    }
    @Test
    public void whenNotEven() {
        Turn turn = new Turn();
        int[] ar = {300, 56, 77, 3, 9};
        int[] result = turn.back(ar);
        int[] ra = {9, 3, 77, 56, 300};
        assertThat(result, is(ra));
    }
}
