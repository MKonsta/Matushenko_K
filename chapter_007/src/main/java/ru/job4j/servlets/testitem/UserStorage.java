package ru.job4j.servlets.testitem;

import ru.job4j.servlets.webarchitecture.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class UserStorage {
    private static final UserStorage instance = new UserStorage();
    private UserStorage(){
    }

    private List<User> users = new CopyOnWriteArrayList<>();

    public List<User> getUsers() {
        return this.users;
    }

    public void add(User user) {
        this.users.add(user);
    }


}
