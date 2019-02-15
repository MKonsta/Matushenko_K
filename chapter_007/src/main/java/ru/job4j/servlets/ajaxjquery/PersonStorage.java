package ru.job4j.servlets.ajaxjquery;

import com.sun.javafx.collections.MappingChange;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersonStorage {
    private static Map<Integer, Person> map = new ConcurrentHashMap<>();

    private PersonStorage() {}

    public static Map<Integer, Person> getMap() {
        return map;
    }
}
