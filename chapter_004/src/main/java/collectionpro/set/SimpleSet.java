package collectionpro.set;

import collectionpro.list.SimpleArrayList;

import java.util.Iterator;

/**
 * 1. Реализовать коллекцию Set на массиве [#996]
 * Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 Коллекция не должна хранить дубликаты.
 Set - внутри для хранения данных использует обычные массивы.
 *
 * Для сокращения кода пользуемся шаблоном проектирования "Композиция". Внутри класса создаем объект SimpleArrayList,
 * и работаем с ним.
 * @param <E>
 */
public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> simpleArray = new SimpleArrayList<>();

    /**
     * Метод проверяет, содржит ли уже simpleArray объект value. Если нет, то добавляет его
     * @param value
     */
    public void add(E value) {
        for (E e : simpleArray) {
            if (e.equals(value)) {
                return;
            }
        }
        simpleArray.add(value);
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}
