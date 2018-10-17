package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//Класс оверрайдит метод close из Autocloseable. Этот метод просто закрывает Connectin conn после завершения работы с базой.
public class Tracker implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private final Properties appProperties = new Properties(); //сюда подключается файл со скриптами app.properties
    private final Properties createTableProperties = new Properties(); //в это и следующее поле также соответствующие файлы
    private final Properties quwryProperties = new Properties();
    private InputStream inputStream = null;

    /**
     * Метод подгружает в поля Properties файлы со скриптами
     * Потом этот метод запускается в конструкторе
     */
    private void loadScrips() {
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("app.properties");
            appProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("createtable.properties");
            createTableProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("query.properties");
            quwryProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Поля для для подключения к базе
     */
    private String url;
    private String login;
    private String password;
    private Connection conn = null;

    public Tracker() {

        loadScrips();
        url = appProperties.getProperty("url");
        login = appProperties.getProperty("login");
        password = appProperties.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, login, password);
            String sqlCommand = createTableProperties.getProperty("create");
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
            String sql = quwryProperties.getProperty("insert");
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
            String sql = quwryProperties.getProperty("update");
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
            String sql = quwryProperties.getProperty("delete");
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
            String sql = quwryProperties.getProperty("findall");
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
            String sql = quwryProperties.getProperty("findbyid");
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
//        tracker.update(3, new Item("mouse", "bujjjhg", "мышь не ездит"));
//        tracker.update(2, new Item("mouse", "вохр", "тоже не ездит"));
//        tracker.delete(2);
//        tracker.delete(4);

//        System.out.println(tracker.findById(1));
    }
}
