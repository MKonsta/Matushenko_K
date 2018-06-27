package collectionpro.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.3.2. Создать контейнер на базе связанного списка  [#159]
 Необходимо создать динамический контейнер с методами:
 1) add(E value);
 2) E get(int index);
 3) реализовать интерфейс Iterable<E>.
 Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора коллекция подверглась структурному изменению,
 итератор должен кидать ConcurrentModificationException.
 Это достигается через введение счетчика изменений - modCount.
 Каждая операция, которая структурно модифицирует коллекцию должна инкрементировать этот счетчик.
 В свою очередь итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
 а затем на каждой итерации сравнивает сохраненное значение, с текущим значением поля modCount, если они отличаются,
 то генерируется исключение.
 * @param <T>
 */
public class SimpleLinkedListContainer<T> implements Iterable<T> {

    /**
     * Счетчик, показывает количество добавленных элементов
     */
    private int size;

    /**
     * Первый узел (элемент) связанного списка
     */
    private Node<T> head;

    /**
     * Метод для добавления элементов
     * @param value
     */
    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> temp = head;

            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<>(value));
        }
        size++;
    }

    /**
     * Метод для получения элементов по индексу
     * @param index
     * @return
     */
    public T get(int index) {
        int currentIndex = 0;
        Node<T> temp = head;
        while (temp != null) {
            if (index == currentIndex) {
                return temp.getDate();
            }
            temp = temp.getNext();
            currentIndex++;
        }
        throw new NoSuchElementException("Noooo!");
    }

    /**
     * Итератор связанного списка
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int modCounter = size;
            int idx = 0;
            Node<T> temp = head;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T result;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (modCounter != size) {
                    throw new ConcurrentModificationException();
                }
                result = temp.getDate();
                temp = temp.getNext();
                return result;
            }
        };
    }

    /**
     * Класс для хранеия элементов
     * @param <T>
     */
    private static class Node<T> {
        /**
         * переменная хранящая данный в текущей ячейке массива
          */
        private T date;

        /**
         * переменная ссылающаяся на следующий элемент массива
         */
        private Node<T> next;

        public T getDate() {
            return date;
        }

        public void setDate(T date) {
            this.date = date;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node(T date) {
            this.date = date;
        }
    }

}
