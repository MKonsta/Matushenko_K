package ru.job4j.servlets.webarchitecturejsp.logic;

import ru.job4j.servlets.webarchitecturejsp.model.User;

public class Demo {
    public static void main(String[] args) {
        Store store = HibernateStore.getInstance();

        store.addUser(new User("Nikolay", "Kruglov", "1", "gq@mail", "admin", "Russia", "Msk"));
    }
}
