package ru.job4j.departamentsort;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartamentSortTest {
    @Test
    public void testSortByIncreasing() {
        List<String> expect = Arrays.asList(new String[]{
                "K1", "K1\\SK1", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K1\\SK2", "K2",
                "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"
        });
        String[] str = {"K2\\SK1\\SSK2", "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        Org org = new Org();
        assertThat(org.sortByIncreasing(str), is(expect));
    }

    @Test
    public void testSortByDecreasing() {
        List<String> expect = Arrays.asList(new String[]{"K2", "K2\\SK1", "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1", "K1", "K1\\SK2",
                "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"
        });
        String[] str = {"K2\\SK1\\SSK2", "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        Org org = new Org();
        assertThat(org.sortByDecreasing(str), is(expect));
    }
}
