package generics;

import java.util.*;

import generics.SimpleArray;
import iterators.Converter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;



import org.junit.Test;

public class SimpleArrayTest {
    @Test
    public void whenAddOneElement() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("Test");
        String[] expect = new String[5];
        expect[0] = "Test";
        assertThat(array, is(expect));
    }
}
