package ru.job4j.generics.hashmap;

import java.util.HashMap;
import java.util.List;

/**
 * 2. Написать программу преобразования List в Map. [#10093]
 * прога конвертирует список List<User> в HashMap<Integer(User.getId), User>
 */

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
