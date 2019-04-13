package com.simple_dao.dao;

import com.simple_dao.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBCDAO implements ClientDAO {

    private Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/carsshop";
        String username = "postgres";
        String password = "kozemir";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(Connection connection, PreparedStatement preparedStatement) {
        if (connection != null && preparedStatement != null) {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        String sql = "INSERT into clients (name, age, phone) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }

    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<Client>();

        Connection connection = null;
        Statement statement = null;

        connection = getConnection();

        try {
            String sql = "select * from clients";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String phone = resultSet.getString(4);
                Client client = new Client(name, age, phone);
                client.setId(id);
                allClients.add(client);
            }
            return allClients;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public Client getById(int id) {
        return null;
    }

    public void remove(int id) {

    }
}
