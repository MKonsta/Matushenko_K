package ru.job4j.array;

import java.util.Arrays;

public class ArrayDublicate {
    public String[] remove(String[] array) {
        int duple = array.length;
        for (int out = 0; out < duple; out++) {
            for (int in = out + 1; in < duple; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[duple - 1];
                    duple--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, duple);
    }

    public static void main(String[] args) {
        ArrayDublicate bb = new ArrayDublicate();
        String[] ara = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        for (String ee : bb.remove(ara)) {
            System.out.println(ee);
        }
    }
}
