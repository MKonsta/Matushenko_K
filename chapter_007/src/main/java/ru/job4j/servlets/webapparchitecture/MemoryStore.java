package ru.job4j.servlets.webapparchitecture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoryStore implements Store{
    private static String url = "jdbc:postgresql://localhost:5432/userstore";
    private static String username = "postgres";
    private static String password = "kozemir";

    //==================Обеспечиваем Singletone==================================
    private static MemoryStore memoryStore;
    public static MemoryStore getMemoryStore() {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String sqlCreateTable = "create table if not exists users(" +
                    "id serial primary key," +
                    " name varchar(30) not null," +
                    " login varchar(30)," +
                    " email varchar(30)," +
                    " createdate varchar(30));";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (memoryStore == null) {
            memoryStore = new MemoryStore();
        }
        return memoryStore;
    }

    private MemoryStore() {    }
    //===========================================================================



    @Override
    public synchronized User add(User user) {
        User resultUser = user;
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "insert into users (name, login, email, createdate) values(?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getCreateDate());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                resultUser.setId(generatedKeys.getInt("id"));
                return resultUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized boolean update(int id, User user) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "update users set name = ?, login = ?, email = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public synchronized boolean delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "delete from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public synchronized List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "select * from users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String login = resultSet.getString(3);
                String email = resultSet.getString(4);
                String createDate = resultSet.getString(5);
                User user = new User(name, login, email, createDate);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "select * from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String login = resultSet.getString(3);
                String email = resultSet.getString(4);
                String createDate = resultSet.getString(5);
                user = new User(name, login, email, createDate);
                user.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public static void main(String[] args) {
//        MemoryStore.getMemoryStore().add(new User("Artem", "Tema", "eee@gmail", "2018-05-04"));
        MemoryStore.getMemoryStore().update(26, new User("Taras", "Tara", "ff@ffff", "gggggggggggg"));
//        System.out.println(MemoryStore.getMemoryStore().delete(3));
//        for (User user : MemoryStore.getMemoryStore().findAll()) {
//            System.out.println(user);
//        }
//        System.out.println(MemoryStore.getMemoryStore().findById(7));
    }


}
