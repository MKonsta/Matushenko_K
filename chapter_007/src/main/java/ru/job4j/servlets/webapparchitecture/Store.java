package ru.job4j.servlets.webapparchitecture;

import java.util.List;

public interface Store {
    public User add(User user);
    public boolean update(int id, User user);
    public boolean delete(int id);
    public List<User> findAll();
    public User findById(int id);
}
