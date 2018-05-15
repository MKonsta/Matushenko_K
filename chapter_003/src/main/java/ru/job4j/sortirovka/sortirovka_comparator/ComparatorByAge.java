package ru.job4j.sortirovka.sortirovka_comparator;

import java.util.Comparator;

public class ComparatorByAge implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        /*if (a.getAge() > b.getAge()) {
            return 1;
        } else if (a.getAge() < b.getAge()) {
            return -1;
        } else {
            return 0;
        }*/
        return a.getAge() - b.getAge();
    }
}
