package ru.job4j.servlets.webarchitecturejsp.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
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

            String timeStamp = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            user.setCreateDate(timeStamp);
            session.save(user);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateUser(int id, User user) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try(SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            user.setId(id);
            User current = session.load(User.class, id);
            user.setCreateDate(current.getCreateDate());
            session.update(user);

            session.getTransaction().commit();
        }
        return true;
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
