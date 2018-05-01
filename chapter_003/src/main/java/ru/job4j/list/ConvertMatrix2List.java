package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3. Конвертация двумерного массива в ArrayList [#48580]
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> convert = new ArrayList<>();
        for (int[] a : array) {
            for (int b : a) {
                convert.add(b);
            }
        }
        return convert;
    }
}
