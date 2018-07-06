package collectionpro.map;

import java.util.*;

/**
 *5. Перекрывать и equals и hashCode [#1002]
 *
 *Теперь переопределили и equals и hashCode. При вставке в map хэшы получились одинаковыми, затем идет проверка по иквелс.
 * Иквелс подтверждает что обьекты одинаковы. И первай элемент мэпа затирается вторым, так как ключт одинаковы.
 * На консоль выводится один элемент
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
    public int hashCode() {

        return Objects.hash(name, birthday);
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
