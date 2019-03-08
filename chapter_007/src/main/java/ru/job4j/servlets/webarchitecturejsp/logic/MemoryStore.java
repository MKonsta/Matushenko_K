package ru.job4j.servlets.webarchitecturejsp.logic;

import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Этот класс для работы с базой пользователей
 */
public class MemoryStore implements Store {

    //============================Singletone================================
    private static MemoryStore memoryStore;

    private static Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public static synchronized MemoryStore getMemoryStore() {
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    private MemoryStore() {

    }
    //===========================================================================

    public static Map<Integer, User> getUserMap() {
        return userMap;
    }
    private AtomicInteger id = new AtomicInteger(0);

    public boolean addUser(User user) {
        for (User tempUser : userMap.values()) {
            if (user.getLogin().equals(tempUser.getLogin()) || user.getEmail().equals(tempUser.getEmail())) {
                return false;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setCreateDate(timeStamp);
        user.setId(id.getAndIncrement());
        userMap.put(user.getId(), user);
        return true;
    }

    public boolean updateUser(int id, User user) {
        if (userMap.containsKey(id)) {
            for (User tempUser : userMap.values()) {
                if (user.getLogin().equals(tempUser.getLogin()) && tempUser.getId() != id
                        || user.getEmail().equals(tempUser.getEmail()) && tempUser.getId() != id) {
                    return false;
                }
            }
            user.setId(id);
            userMap.put(id, user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        return (userMap.remove(id) != null);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(userMap.values());
        return users;
    }

    public User findById(int id) {
        return userMap.get(id);
    }

    public User findByLogin(String login) {
        User result = null;
        for (User user : userMap.values()) {
            if (user.getLogin().equals(login)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public boolean isCredentional(String login, String password) {
        boolean result = false;
        for (User user : userMap.values()) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    result = true;
                }
                break;
            }
        }
        return result;
    }

}
