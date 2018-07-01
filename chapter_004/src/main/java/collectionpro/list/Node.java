package collectionpro.list;


/**
 * 5.3.4. Задан связанный список. Определить цикличность. [#161]
 * Написать алгоритм определяющий, что список содержит замыкания.
 * Обратите внимание, что список может быть замкнут и в середине. К примеру, 3-й узел будет ссылаться на 2-й узел.
 * @param <T>
 */
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Если список зациклен, возвращаем true, если нет - то false.
     * Метод работает на основе алгоритма Черепахи и зайца. Создаем 2 переменные - зайца и черепаху. Черепаха за одну тиерацию делает
     * 1 шаг, заяц делает 2 шага. В конце каждой итерации сравниваем их. Если равны, то список зациклен.  Таким образом мы либо
     * дойддем до конца списка (null), либо черепаха встретится с зайцем
     * @param first
     * @return
     */
    public boolean hasCycle(Node first) {
        boolean res = true;
        if (first == null) {
            return false;
        }
        Node tortle = first;
        Node rabbit = tortle;
        while (true) {
            tortle = tortle.next;
            if (rabbit.next != null) {
                rabbit = rabbit.next.next;
            } else {
                res = false;
                break;
            }

            if (tortle == null || rabbit == null) {
                res = false;
                break;
            }

            if (tortle == rabbit) {
                res = true;
                break;
            }
        }
        return res;
    }
}
