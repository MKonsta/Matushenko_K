package ru.job4j.webarchitecturejsp.logic;


import ru.job4j.webarchitecturejsp.model.User;

import java.util.List;

/**
 * Программа может работать и с БД(DBStore) и с коллекцией(MemoryStore)
 * Чтобы запустить приложение, необходимо после запуска TomCat вбить в браузер http://localhost:8082/chapter_007/usersjsp
 * Login admin passwod 1
 *
 *  */
public class ValidateService {

    //Тут сейчас используется коллекция.
//    private MemoryStore store = MemoryStore.getMemoryStore();
    //Для работы с БД нужно раскомментировать следующую строку, и закомментировать предидущую
    private DBStore store = DBStore.getInstance();

    //==========================Singletone======================================
    private static ValidateService validateService;

    private static final class Holder {
        private static final ValidateService SERVICE = new ValidateService();
    }

    public static ValidateService getValidateService() {
        return Holder.SERVICE;
    }

    private ValidateService() {
        store.addUser(new User("Uriy", "admin", "1", "ura@mail.ru", "1111", "admin", "Kyrgyzstan", "Bishkek"));
        store.addUser(new User("Ivan", "user", "1", "ivan@mail.ru", "1111", "user", "France", "Paris"));
    }
    //============================================================================


    /**
     * Метод для добавления пользователя.
     *
     * @param user
     * @return
     */
    public boolean add(User user) {
        return store.addUser(user);
    }

    /**
     * Метод для обновления данных пользователя.
     *
     * @param id
     * @param user
     * @return
     */
    public boolean update(int id, User user) {
        return store.updateUser(id, user);
    }

    /**
     * Метод для удаления пользователя.
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return store.deleteUser(id);
    }

    /**
     * Метод возвращает список всех пользователей. Сначала проверяет, есть ли в мапе хотябы 1 пользователь
     * @return
     */
    public List<User> findAll() {
        if (store.findAll().size() > 0) {
            return store.findAll();
        }
        return null;
    }

    /**
     * Метод поиска пользователя по id.
     * @param id
     * @return
     */
    public User findById(int id) {
        return store.findById(id);
    }

    /**
     * Поиск пользователя по логину.
     * @param login
     * @return
     */
    public User findByLogin(String login) {
        return store.findByLogin(login);
    }

    /**
     * Метод проверяет - существует ли пользователь с данным логином и паролем
     * @param login
     * @param password
     * @return
     */
    public boolean isCredentional(String login, String password) {
        return store.isCredentional(login, password);
    }

}
