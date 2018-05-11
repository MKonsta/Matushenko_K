package ru.job4j.sortirovka.sortirovka_comparator;

import java.util.Comparator;

public class ComparatorByNameLength implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        if (a.getName().length() > b.getName().length()) {
            return 1;
        } else if (a.getName().length() < b.getName().length()) {
            return -1;
        } else {
            return 0;
        }
    }
}
