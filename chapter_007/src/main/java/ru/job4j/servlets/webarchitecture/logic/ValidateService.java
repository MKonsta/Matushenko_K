package ru.job4j.servlets.webarchitecture.logic;

import ru.job4j.servlets.webarchitecture.model.User;

import java.util.List;

/**
 * В данном классе для работы с базой пользователей во всех методах проходит сначала валидация данных,
 * а затем используются методы из класса ValidateService
 */
public class ValidateService {

    //==========================Singletone======================================
    private static ValidateService validateService;

    public static synchronized ValidateService getValidateService() {
        if (validateService == null) {
            validateService = new ValidateService();
        }
        return validateService;
    }

    private ValidateService() { }
    //============================================================================

    //============================Методы для проверки(валидации) DBStore (Там используется ArrayList)==================================================

//    /**
//     * Метод проверяет, есть ли уже в коллекции юзер с таким же id или email как у добавляемого юзера
//     * Если return == true, значит ни id, ни email не дублируются
//     * @param user
//     * @return
//     */
//    public static boolean addValidate(User user) {
//        for (User newUser : DBStore.getUsers()) {
//            if (newUser.getId() == user.getId() || newUser.getEmail().equals(user.getEmail())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Метод проверяет - уникальность email юзера, данные которого редактируются. email может совпасть только с юзером,
//     * id которого == id редактируемого юзера
//     * Если return == true, значит в коллекции существует юзер с нужным id, и email отредактированного пользователя
//     * не будет дублироваться в коллекции
//     * @param id
//     * @param user
//     * @return
//     */
//    public static boolean updateValidate(int id, User user) {
//        boolean idFlag = false;
//        for (User newUser : DBStore.getUsers()) {
//            if (newUser.getEmail().equals(user.getEmail()) && newUser.getId() != id) {
//                return false;
//            }
//            if (newUser.getId() == id) {
//                idFlag = true;
//            }
//        }
//        return idFlag;
//    }
//
//    /**
//     * Метод проверяет, есть ли в коллекции юзер с заданным id
//     * Если return == true - значит есть
//     * @param id
//     * @return
//     */
//    public static boolean deleteValidate(int id) {
//        for (User user : DBStore.getUsers()) {
//            if (user.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }
    //=======================================================================================


    //==============Методы для проверки(валидации) MemoryStore (Там используется ConcurrentHashMap)===================

    /**
     * Вспомогательный метод для проверки - есть ли уже в мапе пользователи с заданными именем и email'ом
     * Данный метод используется в методе add(User user) данного класса
     * @param email
     * @param login
     * @return
     */
    public boolean addValid(String email, String login) {
        boolean res = true;
        for (User user : MemoryStore.getUserMap().values()) {
            if (user.getEmail().equals(email) || user.getLogin().equals(login)) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Вспомогательный метод для валидации данных при редактировании пользователя
     * @param id
     * @param email
     * @param login
     * @return
     */
    public boolean updateValid(int id, String email, String login) {
        boolean res = true;
        for (User user : MemoryStore.getUserMap().values()) {
            if (user.getEmail().equals(email) && user.getId() != id
                    || user.getLogin().equals(login) && user.getId() != id) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Метод для добавления пользователя. Вначале проходит валидация с помощью метода addValid.
     * И если валидация пройдена, вызывается метод addUser из класса MemoryStore, который добавляет юзера в мапу
     * @param user
     * @return
     */
    public boolean add(User user) {
        if (addValid(user.getEmail(), user.getLogin())) {
            MemoryStore.getMemoryStore().addUser(user);
            return true;
        }
        return false;
    }

    /**
     * Метод для обновления данных пользователя. Сначала проходит валидация
     * @param id
     * @param user
     * @return
     */
    public boolean update(int id, User user) {
        if (MemoryStore.getUserMap().containsKey(id)) {
            if (updateValid(id, user.getEmail(), user.getLogin())) {
                MemoryStore.getMemoryStore().updateUser(id, user);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для удаления пользователя. Вначале проверяет, существует ли пользователь с заданным id
     * @param id
     * @return
     */
    public boolean delete(int id) {
        boolean res = false;
        if (MemoryStore.getUserMap().containsKey(id)) {
            MemoryStore.getMemoryStore().deleteUser(id);
            res = true;
        }
        return res;
    }

    /**
     * Метод возвращает список всех пользователей. Сначала проверяет, есть ли в мапе хотябы 1 пользователь
     * @return
     */
    public List<User> findAll() {
        if (MemoryStore.getUserMap().size() > 0) {
            return MemoryStore.getMemoryStore().findAll();
        }
        return null;
    }

    /**
     * Метод поиска пользователя по id. Сначала проверяет, существует ли пользователь с заданным id
     * @param id
     * @return
     */
    public User findById(int id) {
        if (MemoryStore.getUserMap().containsKey(id)) {
            return MemoryStore.getMemoryStore().findById(id);
        }
        return null;
    }
}
