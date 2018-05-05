package ru.job4j.sortirovka.sortirovkapovozrastu;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1. Организовать сортировку User [#10034]
 * Необходимо создать модель User с полями name, age.
 *Класс User должен реализовать интерфейс Comparable.
 *В классе SortUser написать метод public Set<User> sort (List<User>), который будет возвращать TreeSet пользователей,
 отсортированных по возрасту в порядке возрастания.
 */

public class SortUser {
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>();
        for (User user : users) {
            result.add(user);
        }
        return result;
    }
}
