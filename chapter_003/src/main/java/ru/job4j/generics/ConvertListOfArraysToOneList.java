package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Конвертация листа массивов в один лист Integer [#10037]
 */
public class ConvertListOfArraysToOneList {
    List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int num : array) {
                result.add(num);
            }
        }
        return result;
    }
}
