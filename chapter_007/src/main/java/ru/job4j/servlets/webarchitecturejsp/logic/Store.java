package ru.job4j.servlets.webarchitecturejsp.logic;

import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.util.List;

public interface Store {

    public boolean addUser(User user);

    public boolean updateUser(int id, User user);

    public boolean deleteUser(int id);

    public List<User> findAll();

    public User findById(int id);
}
