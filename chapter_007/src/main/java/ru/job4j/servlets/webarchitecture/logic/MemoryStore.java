package ru.job4j.servlets.webarchitecture.logic;

import ru.job4j.servlets.webarchitecture.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore {

    private static MemoryStore memoryStore;

    private static Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public static synchronized MemoryStore getMemoryStore() {
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    private MemoryStore() { }

    public static Map<Integer, User> getUserMap() {
        return userMap;
    }
    private AtomicInteger id = new AtomicInteger(0);

    public boolean addUser(User user) {
        if (ValidateService.getValidateService().addValid(user.getEmail(), user.getLogin())) {
            user.setId(id.getAndIncrement());
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public boolean updateUser(int id, User user) {
        if (userMap.containsKey(id)) {
            if (ValidateService.getValidateService().updateValid(id, user)) {
                user.setId(id);
                userMap.put(id, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        return userMap.remove(id) != null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(userMap.values());
        return users;
    }

    public User findById(int id) {
        return userMap.get(id);
    }

    public static void main(String[] args) {
        MemoryStore.getMemoryStore().addUser(new User("Ivan", "Vano", "rrr", "555"));
    }
}
