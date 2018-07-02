package collectionpro.set;

import collectionpro.list.SimpleLinkedListContainer;

import java.util.Iterator;

/**
 * 2. Set реализованный на связном списке. [#997]
 *  Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 Коллекция не должна хранить дубликаты.
 Set - внутри для хранения данных использует обычные массивы.
 *
 * Для сокращения кода пользуемся шаблоном проектирования "Композиция". Внутри класса создаем объект SimpleArrayList,
 * и работаем с ним.
 *
 * @param <T>
 */

public class SimpleSetOnLinkedList<T> implements Iterable<T> {

    private SimpleLinkedListContainer<T> simpleLinkedListContainer = new SimpleLinkedListContainer<>();

    /**
     * Метод проверяет, содржит ли уже simpleArray объект value. Если нет, то добавляет его
     * @param value
     */
    public void add(T value) {
        for (T t : simpleLinkedListContainer) {
            if (t.equals(value)) {
                return;
            }
        }
        simpleLinkedListContainer.add(value);
    }

    @Override
    public Iterator<T> iterator() {
        return simpleLinkedListContainer.iterator();
    }
}
