package ru.job4j.servlets.webarchitecturejsp.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Store store = HibernateStore.getInstance();
        User user = new User("Kiril", "kirya", "1", "ewrtt", "Admin", "Usa", "Washik");

        System.out.println(store.updateUser(10, user));

    }
}
