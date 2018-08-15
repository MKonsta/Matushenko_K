package threads.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. Класс хранилища пользователей UserStorage [#1104]
 * 1. Создать класс - структуру данных для хранение пользователей UserStorage.
 * 2. Хранилище должно иметь методы boolean add (User user), boolean update(User user), boolean delete(User user).
 * 3. И особый метод transfer(int fromId, int toId, int amount);
 * 4. Структура данных должна быть потокобезопасная;
 * 5. В структуре User Должны быть поля int id, int amount.
 * amount - это сумма денег на счете пользователя.
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> store = new ArrayList<>();

    public synchronized List<User> getUsers() {
        return this.store;
    }

    /**
     * Метод для добавления юзеров в хранилище
     * @param user
     * @return
     */
    public synchronized boolean add(User user) {
        if (!store.contains(user)) {
            store.add(user);
            return true;
        }
        return false;
    }

    /**
     * Метод для обновления данных пользователя
     * @param user
     * @return
     */
    public synchronized boolean update(User user) {
        if (store.contains(user)) {
            store.remove(user);
            store.add(user);
            return true;
        }
        return false;
    }

    /**
     * Метод для удаления пользователя
     * @param user
     * @return
     */
    public synchronized boolean delete(User user) {
        if (store.contains(user)) {
            store.remove(user);
            return true;
        }
        return false;
    }

    /**
     * Метод для перевода денег с одного пользователя к другому
     * @param fromId - id пользователя с которого будет списание денег
     * @param toId - id пользователя, на которого поступят деньги
     * @param amount - переводимая сумма
     * @return
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int indexToId = 0;
        if (store.contains(new User(fromId, 0)) && store.contains(new User(toId, 0))) {
            for (User user : store) {
                if (user.getId() == fromId) {
                    if (user.getAmount() >= amount) {
                        user.setAmount(user.getAmount() - amount);
                    } else {
                        return false;
                    }
                }
                if (user.getId() == toId) {
                    indexToId = store.indexOf(user);
                }
            }
            store.get(indexToId).setAmount(store.get(indexToId).getAmount() + amount);
            return true;
        }
        return false;
    }
}
