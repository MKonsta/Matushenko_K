package ru.job4j.array;

public class FindLoop {
    public int indexOf(int[] data, int el) {
        int rsl = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        FindLoop a = new FindLoop();
        int[] r = {3, 6, 8, 72, 25};
        System.out.println(a.indexOf(r, 72));
    }
}
