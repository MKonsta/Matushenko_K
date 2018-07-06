package collectionpro.map;

import java.util.*;

/**
 *4. Переопределить только equals [#1004]
 *
 * Теперь мыпереопределили только equels. Хеши не переопредоелили. И Хэшкоды у нас остаются разными. Джава видит что хэши разные,
 * и это гарантирует что объекты тоже разные. До метода иквелс (который в данном сдучае верно определил бы что объекты идентичны)
 * очередь не доходит. И оба объекта вставляются в мап
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + ", children=" + children
                    + '}';
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

    }
}
