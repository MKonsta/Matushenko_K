package ru.job4j.array;

public class Square {
    public int[] calculate(int bound) {
        int[] rst = new int[bound + 1];
        for (int i = 0; i < bound + 1; i++) {
            rst[i] = i * i;
        }
        return rst;
    }

    public static void main(String[] args) {
        Square r = new Square();
        for (int e : r.calculate(2)) {
            System.out.println(e);
        }
    }
}
