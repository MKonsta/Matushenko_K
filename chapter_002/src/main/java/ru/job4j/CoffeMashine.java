package ru.job4j;

public class CoffeMashine {
    int[] changes(int value, int price) {
        int cha = value - price;
        int size = 0;
        while (cha != 0) {
            if (cha >= 10) {
                cha -= 10;
                size++;
            } else if (cha >= 5) {
                cha -= 5;
                size++;
            } else if (cha >= 2) {
                cha -= 2;
                size++;
            } else if (cha >= 1) {
                cha -= 1;
                size++;
            }
        }
        int[] coins = new int[size];
        cha = value - price;
        int index = 0;
        while (cha != 0) {
            if (cha >= 10) {
                coins[index] = 10;
                cha -= 10;
                index++;
            } else if (cha >= 5) {
                coins[index] = 5;
                cha -= 5;
                index++;
            } else if (cha >= 2) {
                coins[index] = 2;
                cha -= 2;
                index++;
            } else if (cha >= 1) {
                coins[index] = 1;
                cha -= 1;
            }
        }
        return coins;
    }

    public static void main(String[] args) {
        CoffeMashine coffeMashine = new CoffeMashine();
        for (int i : coffeMashine.changes(50, 2)) {
            System.out.println(i);
        }
    }
}
