package ru.job4j.servlets.webarchitecturejsp.logic;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CitiesDB implements AutoCloseable {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CitiesDB INSTANCE = new CitiesDB();

    public CitiesDB() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/userstore");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("root");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static CitiesDB getInstance() {
        try (Connection connection = SOURCE.getConnection(); Statement statement = connection.createStatement()){
            String sqlCreateTableCountries = "create table if not exists countries (" +
                    "id serial primary key, name varchar(30) not null unique);";
            statement.executeUpdate(sqlCreateTableCountries);

            String sqlCreateTableCities = "create table if not exists cities (" +
                    "id serial primary key," +
                    "name varchar(30) not null unique," +
                    "country_id int not null references countries (id));";
            statement.executeUpdate(sqlCreateTableCities);

            ResultSet resultSet = statement.executeQuery("select count(*) from countries;");
            resultSet.next();
            if (resultSet.getInt(1) < 1) {
                String sqlFillCountries = "insert into countries (name) values ('Russia'), " +
                        "('France'), ('India'), ('Italy');";
                statement.executeUpdate(sqlFillCountries);
            }

            resultSet = statement.executeQuery("select count(*) from cities;");
            resultSet.next();
            if (resultSet.getInt(1) < 1) {
                String sqlFillCities = "insert into cities (name, country_id) values" +
                        " ('Moskow', 1)," +
                        " ('Piter', 1)," +
                        " ('Rostov', 1)," +
                        " ('Tumen', 1)," +
                        " ('Paris', 2)," +
                        " ('Marsel', 2)," +
                        " ('Strasburg', 2)," +
                        " ('Mumbai', 3)," +
                        " ('Kalkutta', 3)," +
                        " ('Goa', 3)," +
                        " ('Rom', 4)," +
                        " ('Milan', 4)," +
                        " ('Palermo', 4)," +
                        " ('Venecia', 4);";
                statement.executeUpdate(sqlFillCities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()){
            String sql = "select * from countries;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String str = resultSet.getString(2);
                result.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getTownsByCountry(String country) {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()){
            String sql = "select cities.name, countries.name from cities " +
                    "join countries on countries.id = cities.country_id where countries.name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String str = resultSet.getString(1);
                result.add(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}
