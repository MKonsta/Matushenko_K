package collectionpro.set;

import java.util.Arrays;


/**
 * 3. Реализовать коллекцию типа Set на базе хэш-таблицы [#998]
 * Создаю ячейку хэш-таблицы (Класс Cell) с двумя полями - ключ и значение
 */
public class SimpleHashSet {

    /**
     * Контейнер на базе массива для хранения ячеек хэштаблицы
     */
    private Cell[] container = new Cell[2];
    /**
     * Количество занятых ячеек в массиве
     */
    private int size = 0;

    public Cell[] getContainer() {
        return container;
    }

    public boolean add(Cell cell) {
        boolean result = true;

        for (int i = 0; i < size; i++) {
            if (container[i].getKey() == cell.getKey()) {
                result = false;
            }
        }

        if (result) {
            if (container.length - 1 == size) {
                Cell[] temp = Arrays.copyOf(container, container.length + 2);
                container = temp;
            }
            container[size++] = cell;
        }
        return result;
    }

    public boolean contains(String str) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (container[i].getValue().equals(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean remove(String str) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (container[i].getValue().equals(str)) {
                System.arraycopy(container, i + 1, container, i, container.length - i - 1);
                size--;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Класс - ячейка хэш таблицы
     */
    public static class Cell {
        private int key;

        private String value;

        public Cell(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Cell{"
                    + "key=" + key
                    + ", value='" + value + '\''
                    + '}';
        }
    }

    public static void main(String[] args) {
        SimpleHashSet shs = new SimpleHashSet();
        shs.add(new Cell(1, "first"));
        shs.add(new Cell(2, "second"));
        shs.add(new Cell(4, "third"));


        System.out.println(shs.remove("second"));

        int i = 0;
        while (shs.getContainer()[i] != null) {
            System.out.println(shs.getContainer()[i]);
            i++;
        }

    }
}
