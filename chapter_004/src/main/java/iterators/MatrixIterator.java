package iterators;

import java.util.Arrays;
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

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    public boolean hasNext() {
        boolean result = false;

        if (values.length > 0 && j < values[i].length) {
            result = true;
        } else {
            while (i < values.length - 1) {
            i++;
            if (0 < values[i].length) {
                j = 0;
                result = true;
                break;
            }
        }
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
