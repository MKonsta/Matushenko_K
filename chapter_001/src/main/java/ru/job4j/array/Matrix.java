package ru.job4j.array;

public class Matrix {
    int[][] multiplie(int size) {
        int[][] matr = new int[size][size];
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                matr[i - 1][j - 1] = i * j;
                //System.out.print(matr[i-1][j-1] + " ");
            }
            //System.out.println("");
        }
        return matr;
    }

    /*public static void main(String[] args) {
        Matrix a = new Matrix();
        a.multiplie(3);
    }*/
}
