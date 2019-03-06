package ru.job4j.webarchitecturejsp.logic;


import ru.job4j.webarchitecturejsp.model.User;

import java.util.List;

public interface Store {

    public boolean addUser(User user);

    public boolean updateUser(int id, User user);

    public boolean deleteUser(int id);

    public List<User> findAll();

    public User findById(int id);

    public User findByLogin(String login);

    public boolean isCredentional(String login, String password);
}
