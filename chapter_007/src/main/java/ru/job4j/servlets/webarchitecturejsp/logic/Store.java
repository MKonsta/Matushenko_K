package ru.job4j.servlets.webarchitecturejsp.logic;

import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.util.List;

public interface Store {

    public boolean addUser(User user);

    public boolean updateUser(int id, User user);

    public boolean deleteUser(int id);

    public List<User> findAll();

    public User findById(int id);

    public User findByLogin(String login);

    /**
     * Метод используется для авторизации. После введения логина-пароля, происходит проверка их
     * идентичности с уже ствующими в базе логином и паролем. Если совпало, то return true
     * @param login
     * @param password
     * @return
     */
    public boolean isCredentional(String login, String password);
}
