package ru.job4j.sortirovka.sortirovka_comparator;

import java.util.*;

/**
 * 2. Сортировка User с использованием Comparator [#10036]
 */

public class SortUser {
    /**
     * Сортировка по длине имени
     * @param users
     * @return
     */
    public List<User> sortNameLength(List<User> users) {
        Comparator<User> comLength = new ComparatorByNameLength();
        TreeSet<User> treeRes = new TreeSet<>(comLength);
        for (User user : users) {
            treeRes.add(user);
        }
        List<User> result = new ArrayList<>();
        for (User user : treeRes) {
            result.add(user);
        }
        return result;
    }

    /**
     * Сортировка по обеим полям - сначала по имени, а потом по возрасту
     * @param users
     * @return
     */
    public List<User> sortByAllFields(List<User> users) {
        Comparator<User> compAll = new ComparatorByName().thenComparing(new ComparatorByAge());
        TreeSet<User> treeRes = new TreeSet<>(compAll);
        for (User user : users) {
            treeRes.add(user);
        }
        List<User> result = new ArrayList<>();
        for (User user : treeRes) {
            result.add(user);
        }
        return result;
    }

    public static void main(String[] args) {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Sergey", 25),
                        new User("Ivan", 30),
                        new User("Sergey", 20),
                        new User("Ivan", 25)
                )
        );
        for (User user : sortUser.sortByAllFields(users)) {
            System.out.println(user);
        }
    }
}
