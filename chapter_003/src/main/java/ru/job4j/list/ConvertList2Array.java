package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3. Конвертация ArrayList в двухмерный массив [#10035]
 */
public class ConvertList2Array {
    /**
     * Метод принимает коллекцию лист, и количество строк в двумерном массиве. Если в массиве не хватает элементов,
     * метод ставит туда нули.
     * @param list - принимаемая коллекция List
     * @param rows - количество рядов (строк) в двумерном массиве
     * @return Возвращает двумерный массив типа int[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((list.size() / (double)rows)); //получили количество столбцов
        int[][] array = new int[rows][cells]; //Создали ммассив для возвращения и в цикле заполнили его
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (index < list.size()) {
                    array[i][j] = list.get(index);
                    index++;
                } else {
                    break;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        ConvertList2Array convertList2Array = new ConvertList2Array();
        Integer[] a = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        int[][] w = convertList2Array.toArray(list, 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(w[i][j] + " ");
            }
            System.out.println("");
        }


    }
}
