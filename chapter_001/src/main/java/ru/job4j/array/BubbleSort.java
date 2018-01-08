package ru.job4j.array;

public class BubbleSort {
    public int[] sort(int[] array) {
        int min = 0;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j != i; j++) {
                if (array[j] > array[j + 1]) {
                    min = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = min;
                }
            }
        }
        return array;
    }
    public static void main(String[] args) {
        BubbleSort b = new BubbleSort();
        int[] www = {6, 5, 4, 3, 2, 1, 0, 10};
        for (int r : b.sort(www)) {
            System.out.println(r);
        }
    }
}
