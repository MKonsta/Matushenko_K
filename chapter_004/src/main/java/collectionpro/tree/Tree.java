package collectionpro.tree;

import java.util.*;

/**
 * 1. Создать элементарную структуру дерева [#1711]
 * @param <E>
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int size;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.getValue().compareTo(parent) == 0) {
                for (Node<E> leaf : element.leaves()) {
                    if (leaf.eqValue(child)) {
                        return false;
                    }
                }
                element.add(new Node<>(child));
                size++;
                result = true;
                break;
            }
            for (Node<E> leaf : element.leaves()) {
                data.offer(leaf);
            }
        }
        return result;
    }

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

    private Queue<Node<E>> treeToList() {
        Queue<Node<E>> data = new LinkedList<>();
        Queue<Node<E>> result = new LinkedList<>();
        data.offer(this.root);
        result.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            for (Node<E> child : element.leaves()) {
                data.offer(child);
                result.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Queue<Node<E>> allElements = treeToList();
            int count = size;

            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    count--;
                    return allElements.poll().getValue();
                } else {
                    throw  new NoSuchElementException();
                }
            }
        };
    }
}
