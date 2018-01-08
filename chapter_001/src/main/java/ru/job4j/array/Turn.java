package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int reserv = 0;
        if (array.length % 2 == 0) {
            for (int index = 0; index != array.length / 2; index++) {
                reserv = array[array.length - 1 - index];
                array[array.length - 1 - index] = array[index];
                array[index] = reserv;
            }
        } else {
            for (int index = 0; index != (array.length - 1) / 2; index++) {
                reserv = array[array.length - 1 - index];
                array[array.length - 1 - index] = array[index];
                array[index] = reserv;
            }
        }
        return array;
    }

}
