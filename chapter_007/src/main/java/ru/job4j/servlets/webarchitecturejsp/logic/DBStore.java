package ru.job4j.servlets.webarchitecturejsp.logic;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.webarchitecturejsp.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class DBStore {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/userstore");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("kozemir");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getInstance() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()){
            String sqlCreateTable = "create table if not exists users("
                    + "id serial primary key,"
                    + " name varchar(30) not null,"
                    + " login varchar(30),"
                    + " email varchar(30),"
                    + " createdate varchar(30));";
            statement.executeUpdate(sqlCreateTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    private AtomicInteger id = new AtomicInteger(0);

    public boolean addUser(User user) {
//        try (Connection connection = SOURCE.getConnection());{
//
//        }
        return true;
    }

    public static void main(String[] args) {
        DBStore.getInstance();
    }
}
