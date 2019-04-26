package ru.job4j.servlets.webarchitecturejsp.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.servlets.webarchitecturejsp.model.User;

public class Demo {
    public static void main(String[] args) {
//        Store store = HibernateStore.getInstance();
//        User user = new User(new User("Andrey", "Kruglov", "1",
//                "gq@mail", "admin", "Russia", "Msk"));
//        store.updateUser(2, user);


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try(SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

//            User user1 = new User(new User("Andrey", "Kruglov", "1",
//                    "gq@mail", "admin", "Russia", "Msk"));
//            user1.setCreateDate("125845");
            User user = session.load(User.class, 2);
//            user.setCreateDate("46346");
//            user.setId(2);
            System.out.println(user);
//            session.update(user);


            session.getTransaction().commit();
        }
    }
}
