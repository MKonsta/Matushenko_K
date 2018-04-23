package ru.job4j;

public class CoffeMashine {
    int[] changes(int value, int price) {
        int[] coins = {10, 5, 2, 1};
        int cha = value - price;
        int size = 0;
        int i = 0;
        while (cha != 0) {
            if (cha >= coins[i]) {
                cha -= coins[i];
                size++;
            } else i++;
        }
        int[] changeArray = new int[size];
        cha = value - price;
        i = 0;
        int index = 0;
        while (cha != 0) {
            if (cha >= coins[i]) {
                cha -= coins[i];
                changeArray[index] = coins[i];
                index++;
            } else i++;
        }
        return changeArray;
    }

    public static void main(String[] args) {
        CoffeMashine cof = new CoffeMashine();
        for (int i : cof.changes(100, 48)) {
            System.out.println(i);
        }
    }
}
