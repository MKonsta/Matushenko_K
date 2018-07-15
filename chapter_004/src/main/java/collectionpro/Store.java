package collectionpro;

import java.util.*;

/**
 * Статистику по коллекции. [#45889]
 *
 */
public class Store {

    /**
     * Хранилище Юзеров
     */
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    /**
     * Добаляет Юзера с уникальным ID в коллекцию
     * @param id
     * @param name
     * @return
     */
    public boolean add(int id, String name) {
        if (users.contains(new User(id, null))) {
            return false;
        }
        users.add(new User(id, name));
        return true;
    }

    /**
     * Редактирует имя юзера по ID
     * @param id
     * @param name
     * @return
     */
    public boolean update(int id, String name) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(name);
                return true;
            }
        }
        return false;
    }

    /**
     *  Удаляет юзера по ID
     * @param id
     * @return
     */
    public boolean remove(int id) {
        return users.remove(new User(id, null));
    }

    /**
     * метод должен возвращать статистику об изменении коллекции.
     *Сколько добавлено новых.
     *Сколько изменено. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
     *Сколько удалено.
     * @param previous
     * @param current
     * @return
     */
    Info diff(List<User> previous, List<User> current) {
        int add = 0;
        int update = 0;
        int delete = 0;

        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.id, user.name);
        }

        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.id, user.name);
        }

        for (Map.Entry<Integer, String> entry : previousMap.entrySet()) {
            if (currentMap.containsKey(entry.getKey())) {
                if (!currentMap.get(entry.getKey()).equals(entry.getValue())) {
                    update++;
                }
                currentMap.remove(entry.getKey());
            } else {
                delete++;
            }
        }
        add = currentMap.size();
        return new Info(add, update, delete);
    }

    static class User {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id;
        }

        @Override
        public int hashCode() {

            return Objects.hash(id);
        }

        @Override
        public String  toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

    static class Info {
        private int add;
        private int update;
        private int delete;

        @Override
        public String toString() {
            return "Info{" + "add=" + add + ", update=" + update + ", delete=" + delete + '}';
        }

        public Info(int add, int update, int delete) {
            this.add = add;
            this.update = update;
            this.delete = delete;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return add == info.add && update == info.update && delete == info.delete;
        }

        @Override
        public int hashCode() {
            return Objects.hash(add, update, delete);
        }
    }

}
