package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDublicateTest {
    @Test
        public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDublicate ard = new ArrayDublicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expect = {"Привет", "Мир", "Супер"};
        String[] result = ard.remove(input);
        assertThat(result, is(expect));
    }
}
