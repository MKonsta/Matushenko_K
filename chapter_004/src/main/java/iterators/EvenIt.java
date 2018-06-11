package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа [#150]
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */

public class EvenIt implements Iterator {

    private final int[] numbers;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
    }

    private int index = 0;

    public boolean hasNext() {
        boolean result = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    result = true;
                    index = i;
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
        index++;
        return numbers[index - 1];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
