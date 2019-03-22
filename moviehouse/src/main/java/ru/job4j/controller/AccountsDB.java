package ru.job4j.controller;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountsDB {
    private static BasicDataSource SOURCE = new BasicDataSource();
    private static AccountsDB INSTANCE = new AccountsDB();

    public AccountsDB() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/moviehouse");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("kozemir");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static AccountsDB getInstance() {
        try(Connection connection = SOURCE.getConnection(); Statement statement = connection.createStatement()) {
            String sqlCreateTable = "create table if not exists accounts(" +
                    "id serial primary key," +
                    "name varchar(30)," +
                    "phone int);";
            statement.executeUpdate(sqlCreateTable);

            ResultSet resultSet = statement.executeQuery("select count(*) from accounts");
            resultSet.next();
            if (resultSet.getInt(1) < 1) {
                String sql = "insert into accounts (name, phone) values (" +
                        "'Ivanov Sergey', 123)," +
                        "('Petrov Andrey', 123)," +
                        "('Arubov Denis', 123)," +
                        "('1', 1);";
                statement.executeUpdate(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }
}
