package ru.job4j.sortirovka.sortirovkacomparator;

import java.util.Comparator;

public class ComparatorByName implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        return a.getName().compareTo(b.getName());
    }
}
