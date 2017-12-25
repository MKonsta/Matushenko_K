package ru.job4j.loop;

public class Factorial {
    public int calc(int n) {
        int rez = 1;
        for (int i = 1; i < n + 1; i++) {
            rez = rez * i;
        }
        return rez;
    }

}
