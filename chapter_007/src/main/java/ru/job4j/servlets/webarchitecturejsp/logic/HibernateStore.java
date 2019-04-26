package ru.job4j.servlets.webarchitecturejsp.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.util.List;

public class HibernateStore implements Store {

    private static HibernateStore INSTANCE;

    private HibernateStore() {}

    public static HibernateStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HibernateStore();
        }
        return INSTANCE;
    }

    @Override
    public boolean addUser(User user) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try(SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }


        return false;
    }

    @Override
    public boolean updateUser(int id, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public boolean isCredentional(String login, String password) {
        return false;
    }
}
