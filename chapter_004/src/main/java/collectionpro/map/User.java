package collectionpro.map;

import java.util.*;

/**
 *3. Переопределить только hashCode [#1003]
 *
 * Мы добавляем в карту два элемента, по сути с одинаковыми ключами. И они не затирают друг друга. Это происходит по тому,
 * что в кдассе юзер не переопределены методы hashCode и equals. Эти методы напрямую используются из класса Object.
 * Java сравнивает их не по содержанию полей, а просто как разные ссылки в памяти. Поэтому они определяются как разные,
 * и добавляютя в мэп  в качестве ключей.
 *В данном случае мы переопределили только хэшкод. Теперь одинаковые объекты по хэшкоду равны. Но для добавления в мапу
 * этого недостаточно. После того как Джва установила что хэши одинаковы, потом еще проверяет по ивкэлс.
 * А иквелс у нас не переопределен, и этот метод показывает что объекты разные. По умолчанию в классе Object иквелс проверяет
 * ссылается ли новый объект на таекущий, и все. Поэтому в мэп добавились оба объекта.
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
