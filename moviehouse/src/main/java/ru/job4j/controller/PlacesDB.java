package ru.job4j.controller;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.service.Account;
import ru.job4j.service.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlacesDB implements AutoCloseable {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final PlacesDB INSTANCE = new PlacesDB();


    public PlacesDB() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/moviehouse");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("kozemir");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static PlacesDB getInstance() {
        try(Connection connection = SOURCE.getConnection(); Statement statement = connection.createStatement()) {
            String sqlCreateTable = "create table if not exists places(" +
                    "id serial primary key," +
                    "price int," +
                    "condition boolean);";
            statement.executeUpdate(sqlCreateTable);

            ResultSet resultSet = statement.executeQuery("select count(*) from places;");
            resultSet.next();
            if (resultSet.getInt(1) < 1) {
                String sql;
                for (int i = 0; i < 9; i++) {
                    if (i > 2 && i < 6) {
                        sql = "insert into places (price, condition) values (300, false)";
                    } else {
                        sql = "insert into places (price, condition) values (200, false)";
                    }
                    statement.executeUpdate(sql);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    public List<Place> getAllPlaces() {
        List<Place> result = new ArrayList<>();

        try(Connection connection = SOURCE.getConnection()) {
            String sql = "select * from places order by id;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int num = resultSet.getInt(1);
                int price = resultSet.getInt(2);
                boolean condition = resultSet.getBoolean(3);
                result.add(new Place(num, price, condition));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Place getPlaceById(int id) {
        Place result = null;
        try {
            String sql = "select * from places where id = ?;";
            PreparedStatement preparedStatement = SOURCE.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int num = resultSet.getInt(1);
                int price = resultSet.getInt(2);
                boolean condition = resultSet.getBoolean(3);
                result = new Place(num, price, condition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void occupyPlace(int num) {
        try(Connection connection = SOURCE.getConnection()) {
            String sql = "update places set condition = true where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, num);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificationAndOccupie(Account account, int placesId) {
        Connection connection = null;
        boolean result = false;
        try  {
            connection = SOURCE.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            if (AccountsDB.getInstance().getExists(account)) {
                if (getPlaceById(placesId).isCondition() == false) {
                    occupyPlace(placesId);
                    result = true;
                    connection.commit();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }


}
