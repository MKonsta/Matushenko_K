package collectionpro.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 *8. Реализовать собственную структуру данных - HashMap [#1008]

 Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 boolean insert(K key, V value);
 V get(K key);
 boolean delete(K key);

 Реализовывать итератор.
 Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.

 Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.

 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V> implements Iterable<Entry<K, V>> {


    /**
     * Хранилище пар "Ключ-значение"
     */
    private Entry[] entrySet = new Entry[100];

    /**
     * Количество элементов в массиве
     */
    private int size = 0;

    /**
     * Хэш функция, которая присваивает индекс новому добавляемому элементу
     * @param key
     * @return
     */
    private int hash(K key) {
        return (31 * 17 + key.hashCode()) % entrySet.length;
    }

    /**
     * Функция увеличивает массив вдвое и возвращает его. (Все элементы сохраняются)
     */
    private void resize() {
        Entry[] temp = new Entry[entrySet.length * 2];
        System.arraycopy(entrySet, 0, temp, 0, entrySet.length);
        entrySet = temp;
    }

    /**
     * Метод добавления новых элементов в массив. Коллизии неразрешены
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        if (entrySet[hash(key)] == null) {
            if (size / entrySet.length >= 0.8) {
                resize();
            }
            entrySet[hash(key)] = new Entry(key, value);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Метод получения значения по ключу
     * @param key
     * @return
     */
    public V get(K key) {
        if (entrySet[hash(key)] != null) {
            return (V) entrySet[hash(key)].getValue();
        }
        return null;

    }

    /**
     * Метод удаления элемента из коллекции по ключу
     * @param key
     * @return
     */
    public boolean delete(K key) {
        if (entrySet[hash(key)] != null) {
            entrySet[hash(key)] = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Метод показывает, существует ли элемент с таким ключом в коллекции
     * @param key
     * @return
     */
    public boolean contains(K key) {
        if (entrySet[hash(key)] != null) {
            return true;
        }
        return false;
    }

    /**
     * Итератор
     * @return
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            int idx = 0;
            @Override
            public boolean hasNext() {
                while (idx < entrySet.length) {
                    if (entrySet[idx] != null) {
                        return true;
                    }
                    idx++;
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return entrySet[idx++];
            }
        };
    }



}

/**
 * Класс - ячейка для хранения пары "Ключ-значение". В этом классе переопределены HashCode и equals
 * @param <K>
 * @param <V>
 */
class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "Entry{"
                + "key=" + key
                + ", value=" + value
                + '}';
    }
}