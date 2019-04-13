package com.simple_dao.dao;

import com.simple_dao.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDao implements CarDAO {

    private Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/carsshop";
        String username = "postgres";
        String password = "kozemir";

        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Добавление новой машины
     * @param car
     */
    public void add(Car car) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement statement;

        try {

            int markId = getMarkId(car.getMark(), connection);

            if (markId == -1) {
                statement = connection.prepareStatement("insert into marks (mark) values (?)");
                statement.setString(1, car.getMark());
                System.out.println(car.getMark());
                statement.execute();

                statement = connection.prepareStatement("select max(id) from marks;");
                ResultSet rs = statement.executeQuery();
                rs.next();
                markId = rs.getInt(1);
            }

            statement = connection.prepareStatement("insert into cars (mark_id, model, price) values(?, ?, ?)");
            statement.setInt(1, markId);
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод возвращает Id машины по марке
     * @param markName
     * @return
     */
    private int getMarkId(String markName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM marks WHERE mark = ? ");
            preparedStatement.setString(1, markName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<Car>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String sql = "SELECT cars.id, marks.mark, cars.model, cars.price FROM cars INNER JOIN marks ON marks.id = cars.mark_id;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String mark = resultSet.getString(2);
                String model = resultSet.getString(3);
                int price = resultSet.getInt(4);
                Car car = new Car(mark, model, price);
                car.setId(id);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCars;
    }

    public Car getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Car result = null;

        try {
            connection = getConnection();
            String sql = "select cars.id, marks.mark, cars.model, cars.price from cars join marks on cars.mark_id = marks.id where cars.id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String mark = resultSet.getString(2);
                String model = resultSet.getString(3);
                int price = resultSet.getInt(4);
                result = new Car(mark, model, price);
                result.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void updatePrice(int price, int carId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        try {
            String sql = "update cars set price = ? where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, carId);
            int updatedValues = preparedStatement.executeUpdate();
            System.out.println("Values updated: " + updatedValues);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void remove(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        try {
            String sql = "delete from cars where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String mark) {
        int result = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        int id = getMarkId(mark, connection);
        try {
            String sql = "delete from cars where mark_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            System.out.println("Deleted " + result + " cars");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CarJDBCDao carJDBCDao = new CarJDBCDao();
        carJDBCDao.remove("Lada");
    }
}
