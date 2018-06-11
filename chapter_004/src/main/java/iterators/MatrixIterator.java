package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][] [#9539]
 * Необходимо создать итератор для двухмерного массива.
 *
 * int[][] value = {
 {1, 2}
 {3, 4}
 };

 метод next = должен вернуть последовательно 1, 2, 3, 4.
 */

public class MatrixIterator implements Iterator {
    private final int[][] values;

    private int i = 0;
    private int j = 0;

    public MatrixIterator(int[][] valies) {
        this.values = valies;
    }


    public boolean hasNext() {
        boolean result = false;
        if (i < values.length && j < values[i].length) {
            result = true;
        } else if (i < values.length - 1 && j == values[i].length) {
            i++;
            j = 0;
            result = true;
        } else if (i == values.length - 1 && j == values[i].length - 1) {
            result = false;
        }

        return result;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        j++;
        return values[i][j - 1];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
