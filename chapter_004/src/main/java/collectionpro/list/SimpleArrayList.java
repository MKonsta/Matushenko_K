package collectionpro.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.3.1. Создать динамический список на базе массива. [#158]
 Необходимо создать динамический контейнер с методами:

 1) add(E value);

 2) E get(int index);

 3) реализовать интерфейс Iterable<E>.

 Внутри контейнер должен базироваться на массиве (Object[] container). Использовать стандартные коллекции JDK (ArrayList, LinkedList и т.д.) запрещено.
 Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.

 Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора коллекция подверглась структурному изменению,
 итератор должен кидать ConcurrentModificationException.
 Это достигается через введение счетчика изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию должна инкрементировать этот счетчик.
 В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount), а затем на каждой итерации сравнивает сохраненное значение,
 с текущим значением поля modCount, если они отличаются, то генерируется исключение.

 * @param <E>
 */
public class SimpleArrayList<E> implements Iterable<E> {
    /**
     * Счетчик количества элементов в массиве.
     */
    private int counter = 0;

    /**
     * Котнтейнер для добавления объектов.
     */
    private Object[] container = new Object[2];

    /**
     * Метод добавления новых объектов. При заполнении массива, Контейнер расширяется
     * @param value
     */
    public void add(E value) {
        if (counter == container.length) {
            Object[] tempContainer = Arrays.copyOf(container, container.length + 2);
            container = tempContainer;
        }
        container[counter] = value;
        counter++;
    }

    /**
     * Метод получения объектов из контейнера по индексу
     * @param index
     * @return
     */
    public E get(int index) {
        return (E) container[index];
    }

    /**
     * Итератор контейнера. Если во время итерации массив как-то модифицируется, то выбрасывается исключение ConcurrentModificationException
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int modCount = counter;
            private int pos = 0;
            @Override
            public boolean hasNext() {
                if (modCount != counter) {
                    throw new ConcurrentModificationException();
                }
                return pos < counter;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("nooooooooooo");
                } else if (modCount != counter) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[pos++];
            }
        };
    }

}
