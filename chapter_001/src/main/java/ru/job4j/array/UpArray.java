package ru.job4j.array;

public class UpArray {
    public int[] ar(int[] arOne, int[] arTwo) {
        //Сравниваем массивы подлине, и перезаписываем большой в big, малый в small
       int[] small;
       int[] big;
        if (arOne.length < arTwo.length) {
           small = arOne;
           big = arTwo;
       } else {
           small = arTwo;
           big = arOne;
       }
       int[] res = new int[arOne.length + arTwo.length];
        //индекс большего массива
        int bi = 0;
        //индекс малого массива
        int sm = 0;
        int index = 0;
       for (index = sm; sm != small.length; index++) {
            if (small[sm] < big[bi]) {
                res[index] = small[sm];
                sm++;
            } else {
                res[index] = big[bi];
                bi++;
            }
       }
       for (int j = index; j < res.length; j++) {
           res[j] = big[bi++];
       }
       return res;
    }

    public static void main(String[] args) {
        //int[] a = {5, 10, 15, 20, 50,};
        //int[] b = {6, 7,  8,  18, 19, 23, 48, 63};
        int[] a = {54, 68, 73, 80};
        int[] b = {30, 35, 55};
        UpArray dd = new UpArray();
        for (int e : dd.ar(a, b)) {
            System.out.println(e);
        }
    }
}
