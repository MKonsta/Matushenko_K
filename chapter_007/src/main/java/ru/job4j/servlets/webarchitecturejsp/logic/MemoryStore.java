package ru.job4j.servlets.webarchitecturejsp.logic;

import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Этот класс для работы с базой пользователей
 */
public class MemoryStore {

    //============================Singletone================================
    private static MemoryStore memoryStore;

    private static Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public static synchronized MemoryStore getMemoryStore() {
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    private MemoryStore() { }
    //===========================================================================

    public static Map<Integer, User> getUserMap() {
        return userMap;
    }
    private AtomicInteger id = new AtomicInteger(0);

    public boolean addUser(User user) {
        user.setId(id.getAndIncrement());
        userMap.put(user.getId(), user);
        return true;
    }

    public boolean updateUser(int id, User user) {
        userMap.put(id, user);
        return true;
    }

    public boolean deleteUser(int id) {
        userMap.remove(id);
        return true;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(userMap.values());
        return users;
    }

    public User findById(int id) {
        return userMap.get(id);
    }

}
