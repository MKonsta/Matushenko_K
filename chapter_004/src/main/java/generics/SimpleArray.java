package generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    Object[] array;

    private int index = 0;
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        if (index == array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
        index++;
    }

    public void set(int index, T model) {
        array[index] = model;
    }

    public void delete(int index) {
        array[index] = null;
    }

    public T get(int index) {
        return (T) array[index];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                return (T) array[index - 1];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        SimpleArray<String> ss = new SimpleArray(5);
        ss.add("DDDDDDDD");
        System.out.println(ss);
    }
}

