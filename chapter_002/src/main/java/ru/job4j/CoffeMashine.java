package ru.job4j;

public class CoffeMashine {
    int[] changes(int value, int price) {
        int cha = value - price;
        int len = 0;
        int temp_cha = 0;
        //Сначала высчитываем длинну массива (общее количество монет)
        if (temp_cha != cha && cha > 9) {
            while (cha - temp_cha > 9) {
                temp_cha = temp_cha + 10;
                len++;
            }
        }
        if (temp_cha != cha && cha % 10 > 4) {
            temp_cha = temp_cha + 5;
            len++;
        }
        if (temp_cha != cha && cha - temp_cha > 1) {
            while (cha - temp_cha > 1) {
                temp_cha = temp_cha + 2;
                len++;
            }
        }
        if (temp_cha != cha && cha - temp_cha == 1) {
            temp_cha = temp_cha + 1;
            len++;
        }

        int[] temp = new int[len];
        temp_cha = 0;
        int index = 0;

        //Теперь используя теже циклы заполняем наш массив номиналами монет 10, 5, 2, и 1
        if (temp_cha != cha && cha > 9) {
            while (cha - temp_cha > 9) {
                temp_cha = temp_cha + 10;
                temp[index] = 10;
                index++;
            }
        }
        if (temp_cha != cha && cha % 10 > 4) {
            temp_cha = temp_cha + 5;
            temp[index] = 5;
            index++;
        }
        if (temp_cha != cha && cha - temp_cha > 1) {
            while (cha - temp_cha > 1) {
                temp_cha = temp_cha + 2;
                temp[index] = 2;
                index++;
            }
        }
        if (temp_cha != cha && cha - temp_cha == 1) {
            //temp_cha = temp_cha + 1;
            temp[index] = 1;
        }
        return temp;
    }

    public static void main(String[] args) {
        CoffeMashine coffeMashine = new CoffeMashine();
        for (int i : coffeMashine.changes(50, 31)) {
            System.out.println(i);
        }
    }
}
