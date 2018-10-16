package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 4.1. Трекер [#1734]
 * Взять класс Tracker  2. Реализовать класс Tracker [#396] и расширьте для него интерфейс AutoCloseable.
 * Необходимо заменить в классе Tracker хранение данных из массива в базу данных.
 * Настройки подключение базы данных и скрипты должны находиться в отдельном файле и считываться при старте.
 * Предусмотреть возможность, что структуры в базе еще нет. И вам нужно ее создать при старте.
 * Все ресурсы необходимо закрывай через try-with-resources
 * В классе трекер появляется новое поле private Connection connection. Его нужно закрывать через AutoCloseable.
 */

public class Tracker implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private final Properties properties = new Properties();
    private InputStream inputStream = null;

    private String url;
    private String login;
    private String password;
    private Connection conn = null;

    public Tracker() {

        try {
            inputStream = new FileInputStream("app.properties");
            properties.load(inputStream);
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            conn = DriverManager.getConnection(url, login, password);
            String sqlCommand = "create table tracker(id serial primary key, name varchar(200), desk varchar(200), created timestamp, comment varchar(2000))";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("new table succesful created");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Item add(Item item) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "insert into tracker(name, desk, created, comment) values(?, ?, ?, ?)";
            PreparedStatement prepSt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepSt.setString(1, item.getName());
            prepSt.setString(2, item.getDesk());
            prepSt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            prepSt.setString(4, item.getComments());
            prepSt.executeUpdate();
            ResultSet generatedKeys = prepSt.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(generatedKeys.getInt("id"));
                item.setCreated(generatedKeys.getString("created"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    public void update(int id, Item item) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "update tracker set name = ?, desk = ?, comment = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesk());
            preparedStatement.setString(3, item.getComments());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Item updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "delete from tracker where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Item has been deleted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "select * from tracker";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("desk"), rs.getString("comment"));
                item.setId(rs.getInt("id"));
                item.setCreated(rs.getString("created"));
                items.add(item);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    public Item findById(int idOfItem) {
        Item item = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "select * from tracker where id = ?";
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setInt(1, idOfItem);
            prepSt.executeQuery();
            ResultSet rs = prepSt.getResultSet();
            if (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("desk"), rs.getString("comment"));
                item.setId(rs.getInt("id"));
                item.setCreated(rs.getString("created"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
//        tracker.add(new Item("printer", "otk", "принтер не печатает"));
//        tracker.add(new Item("mouse", "buhg", "мыш не ездит"));
//        tracker.add(new Item("monitor", "buhg", "monitor dont work"));
//        for (Item item : tracker.findAll()) {
//            System.out.println(item);
//        }
//        tracker.update(2, new Item("mouse", "bujjjhg", "мышь не ездит"));
//        tracker.update(2, new Item("mouse", "вохр", "тоже не ездит"));
//        tracker.delete(3);
//        tracker.delete(4);

        System.out.println(tracker.findById(3));
    }
}
