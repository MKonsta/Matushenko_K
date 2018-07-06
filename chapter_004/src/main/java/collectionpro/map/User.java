package collectionpro.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 2. Не перекрывать equals hashCode [#1005]
 *
 *
 * Мы добавляем в карту два элемента, по сути с одинаковыми ключами. И они не затирают друг друга. Это происходит по тому,
 * что в кдассе юзер не переопределены методы hashCode и equals. Эти методы напрямую используются из класса Object.
 * Java сравнивает их не по содержанию полей, а просто как разные ссылки в памяти. Поэтому они определяются как разные,
 * и добавляютя в мэп  в качестве ключей
 */
class User {
        private String name;
        private int children;
        private Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public static void main(String[] args) {
        User user = new User("Ivan", 2, new GregorianCalendar(1980, 4, 8));
        User user1 = new User("Ivan", 2, new GregorianCalendar(1980, 4, 8));
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user1, new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println(map);
    }
}
