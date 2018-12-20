package ru.job4j.servlets.webarchitecture.logic;

import ru.job4j.servlets.webarchitecture.model.User;

public class ValidateService {
    private static ValidateService validateService;

    public static synchronized ValidateService getValidateService() {
        if (validateService == null) {
            validateService = new ValidateService();
        }
        return validateService;
    }

    private ValidateService() { }

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

    public boolean updateValid(int id, User user) {
        boolean res = true;
        for (User newUser : MemoryStore.getUserMap().values()) {
            if (newUser.getEmail().equals(user.getEmail()) && newUser.getId() != id
                    || newUser.getLogin().equals(user.getLogin()) && newUser.getId() != id) {
                res = false;
                break;
            }
        }
        return res;
    }
}
