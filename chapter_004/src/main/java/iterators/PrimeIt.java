package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.3. Создать итератор простые числа. [#151]
 * Создать итератор возвращающий только простые числа.
 Простым является натуральное число больше 1, которое делится без остатка только на 1 и на себя
 */

public class PrimeIt implements Iterator {
    private final int[] numbers;

    public PrimeIt(int[] numbers) {
        this.numbers = numbers;
    }

    private int index = 0;

    public static boolean checkSimple(int i) {
        if (i <= 1) {
            return false;
        } else if (i <= 3) {
            return true;
        } else if (i % 2 == 0 || i % 3 == 0) {
            return false;
        }
        int n = 5;
        while (n * n <= i) {
            if (i % n == 0 || i % (n + 2) == 0) {
                return false;
            }
            n = n + 6;
        }
        return true;
    }

    public boolean hasNext() {
        boolean result = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (checkSimple(numbers[i])) {
                    result = true;
                    index = i;
                    break;
                }
            }
        }
        return result;
    }

    public Integer next() {
        if (hasNext()) {
            index++;
            return numbers[index - 1];
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
