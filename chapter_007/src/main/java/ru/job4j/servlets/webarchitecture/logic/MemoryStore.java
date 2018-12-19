package ru.job4j.servlets.webarchitecture.logic;

import ru.job4j.servlets.webarchitecture.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore {
    private static Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private static MemoryStore memoryStore;
    public static synchronized MemoryStore getMemoryStore() {
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    private MemoryStore() {}

    public static Map<Integer, User> getUserMap() {
        return userMap;
    }

    public static synchronized boolean addUser(User user) {
        if (ValidateService.addValid(user)) {
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public static synchronized boolean updateUser(User user) {
        if (ValidateService.updateValid(user)) {
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public static synchronized boolean deleteUser(int id) {
        if (ValidateService.deleteValid(id)) {
            MemoryStore.getUserMap().remove(id);
            return true;
        }
        return false;
    }

    public static synchronized List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(userMap.values());
        return users;
    }

    public static synchronized User findById(int id) {
        if (ValidateService.deleteValid(id)) {
            return userMap.get(id);
        }
        return null;
    }

}
