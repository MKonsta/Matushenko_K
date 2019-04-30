package ru.job4j.servlets.webarchitecturejsp.logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HibernateStore implements Store {
    private StandardServiceRegistry registry = null;
    private SessionFactory sessionFactory = null;

    private static HibernateStore INSTANCE;

    private HibernateStore() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static HibernateStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HibernateStore();
        }
        return INSTANCE;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            user.setCreateDate(timeStamp);
            session.save(user);

            session.getTransaction().commit();
            result = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public boolean updateUser(int id, User user) {
        boolean result = false;
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User currentUser = session.load(User.class, id);
            currentUser.setName(user.getName());
            currentUser.setLogin(user.getLogin());
            currentUser.setPassword(user.getPassword());
            currentUser.setEmail(user.getEmail());
            currentUser.setRole(user.getRole());
            currentUser.setCountry(user.getCountry());
            currentUser.setCity(user.getCity());

            session.update(currentUser);
            session.getTransaction().commit();
            result = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteUser(int id) {
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = session.load(User.class, id);

            session.delete(user);
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            users = session.createQuery("from hiber_users").list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user;
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            user = session.load(User.class, id);

            session.getTransaction().commit();
        }

        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user;
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            String hql = "from hiber_users h where h.login = :log";

            List<User> users = session.createQuery(hql).setParameter("log", login).list();
            user = users.get(0);


            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public boolean isCredentional(String login, String password) {
        try(Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            String hql = "from hiber_users h where h.login = :log";
            User user = session.createQuery(hql, User.class).setParameter("log", login).list().get(0);
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }

            session.getTransaction().commit();
        }
        return false;
    }
}
