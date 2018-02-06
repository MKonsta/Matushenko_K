package ru.job4j.array;

public class UpArray {
    int[] arr(int[] one, int[] two) {
        int[] res = new int[one.length + two.length];
        int oneLength = 0;
        int twoLength = 0;
        int index = 0;
        while (oneLength < one.length && twoLength < two.length) {
            if (one[oneLength] < two[twoLength]) {
                res[index] = one[oneLength];
                index++;
                oneLength++;
            } else {
                res[index] = two[twoLength];
                index++;
                twoLength++;
            }
        }
        if (oneLength < one.length) {
            while (index < res.length) {
                res[index] = one[oneLength];
                index++;
                oneLength++;
            }
        } else if (twoLength < two.length){
            while (index < res.length) {
                res[index] = two[twoLength];
                index++;
                twoLength++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] one = {4, 8, 68, 70};
        int[] two = {5, 90, 97, 100};
        UpArray upArray = new UpArray();
        for (int i : upArray.arr(one, two)) {
            System.out.println(i);
        }
    }
}
