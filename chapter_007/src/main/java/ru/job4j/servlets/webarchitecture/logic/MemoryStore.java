package ru.job4j.servlets.webarchitecture.logic;

import ru.job4j.servlets.webarchitecture.model.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryStore {
    private static MemoryStore memoryStore;
    private static List<User> users = new ArrayList<>();

    public static synchronized MemoryStore getMemoryStore() {
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    public static synchronized List<User> getUsers() {
        List<User> result = new ArrayList<>();
        result = users;
        return result;
    }

    private MemoryStore() {}


    public static synchronized boolean addUser(User user) {
        if (ValidateService.addValidate(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    public static synchronized boolean updateUser(int id, User user) {
        if (ValidateService.updateValidate(id, user)) {
            for (User updUser : users) {
                if (updUser.getId() == id) {
                    updUser = user;
                    updUser.setId(id);
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized boolean deleteUser(int id) {
        if (ValidateService.deleteValidate(id)) {
            for (User user : users) {
                if (user.getId() == id) {
                    users.remove(user);
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized List<User> findAll() {
        return users;
    }

    public static synchronized User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MemoryStore.addUser(new User(1, "Ivan", "Vano", "eee@mail.ru", "125844")));
        System.out.println(MemoryStore.getUsers());
        MemoryStore.deleteUser(5);
        System.out.println(MemoryStore.getUsers());
    }
}
