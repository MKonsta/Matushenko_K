package collectionpro.tree;

import java.util.*;

/**
 * 1. Создать элементарную структуру дерева [#1711]
 * @param <E>
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень дерева
     */
    private Node<E> root;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Метод находит узел, и добавлет в его потомки дочерний элемент
     * @param parent parent.
     * @param child child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {

        boolean result = false;
        boolean dubl = false;
        Optional<Node<E>> parentNode = this.findBy(parent);
        if (parentNode.isPresent()) {
            for (Node<E> node : parentNode.get().leaves()) {
                if (node.eqValue(child)) {
                    dubl = true;
                }
            }
            if (!dubl) {
                Node<E> childNode = new Node<>(child);
                parentNode.get().add(childNode);
                result = true;
            }
        }
        return result;
    }

    /**
     * Поиск значения по дереву
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator<E> {
        private Queue<Node<E>> queue = new LinkedList<>();

        SimpleIterator() {
            queue.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            E result;
            if (hasNext()) {
                Node<E> nextNode = queue.poll();
                if (!nextNode.leaves().isEmpty()) {
                    for (Node<E> child : nextNode.leaves()) {
                        queue.add(child);
                    }
                }
                result = nextNode.getValue();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}
