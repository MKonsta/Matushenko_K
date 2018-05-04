package ru.job4j.generics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListOfArraysToOneListTest {
    @Test
    public void whenAddTwoArrays() {
        ConvertListOfArraysToOneList con = new ConvertListOfArraysToOneList();
        List<int[]> arrs = Arrays.asList(new int[]{1, 2,}, new int[]{3, 4, 5, 6});
        List<Integer> result = con.convert(arrs);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }
}
