package threads;

import collectionpro.list.SimpleArrayList;
import collectionpro.list.SimpleLinkedListContainer;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 3. ThreadSafe динамический список  [#1105]
 * Необходимо создать многопоточную коллекцию данных.
 * За счет композиции коллекцих из заданий:
 *  5.3.1. Создать динамический список на базе массива. [#158] и
 *  5.3.2. Создать контейнер на базе связанного списка [#159],
 */
@ThreadSafe
public class ArrayAndLinkedCompozition {

    /**
     * Создаем экземпляр ArrayList'а
     */
    @GuardedBy("this")
    private SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

    /**
     * Создаем объект LinkedList'а
     */
    @GuardedBy("this")
    private SimpleLinkedListContainer<Integer> linkedListContainer = new SimpleLinkedListContainer<>();

    /**
     * Метод добавления элементов в ArrayList
     * @param val
     */
    public synchronized void addToArray(Integer val) {
        arrayList.add(val);
    }

    /**
     * Метод получения элементов по индексу из ArrayList'а
     * @param index
     * @return
     */
    public synchronized Integer getValOfArray(int index) {
        return arrayList.get(index);
    }

    /**
     * Метод добавления элементов в LinkedList
     * @param val
     */
    public synchronized void addToLinked(Integer val) {
        linkedListContainer.add(val);
    }

    /**
     * Метод получения элементов по индексу из LinkedList'а
     * @param index
     * @return
     */
    public synchronized Integer getValOfLinked(int index) {
        return linkedListContainer.get(index);
    }
}
