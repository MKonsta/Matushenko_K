package ru.job4j.trackersql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.startuisecondrealization.ITracker;
import ru.job4j.tracker.startuisecondrealization.Item;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Это вторая реализация трекера на SQL. Этот трекер привязан к трекеру из chapter_002 с помощью
 * интерфейса ITracker. Item соответственно тоже используется из chapter_002
 */

public class TrackerSQL implements ITracker, Closeable {
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private final Properties appProperties = new Properties(); //сюда подключается файл со скриптами app.properties
    private final Properties createTableProperties = new Properties(); //в это и следующее поле также соответствующие файлы
    private final Properties quwryProperties = new Properties();
    private InputStream inputStream = null;

    private String url;
    private String login;
    private String password;
    private Connection conn = null;

    public TrackerSQL() {
        init();
        url = appProperties.getProperty("url");
        login = appProperties.getProperty("login");
        password = appProperties.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, login, password);
            String sqlCommand = createTableProperties.getProperty("createsql");
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("new table succesful created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   public boolean init() {
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
       }
       return this.conn != null;
   }

    @Override
    public Item add(Item item) {
        try {
            String sql = quwryProperties.getProperty("insert");
            PreparedStatement prepSt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepSt.setString(1, item.getName());
            prepSt.setString(2, item.getDesk());
            prepSt.setInt(3, Math.toIntExact(item.getCreated()));
            prepSt.setString(4, item.getComments());
            prepSt.executeUpdate();
            ResultSet generatedKys = prepSt.getGeneratedKeys();
            if (generatedKys.next()) {
                item.setId(String.valueOf(generatedKys.getInt("id")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try {
            String sql = quwryProperties.getProperty("update");
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, item.getName());
            prepSt.setString(2, item.getDesk());
            prepSt.setString(3, item.getComments());
            prepSt.setInt(4, Integer.parseInt(id));
            prepSt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            String sql = quwryProperties.getProperty("delete");
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setInt(1, Integer.parseInt(id));
            prepSt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Item[] findAll() {
        Item[] items = null;
        try {
            String sql = quwryProperties.getProperty("numrows"); //находим длинну массива (количество всех строк в таблице)
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                items = new Item[rs.getInt("count")];
            }
            sql = quwryProperties.getProperty("findall");
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            int index = 0;
            while (rs.next()) {
                items[index] = new Item(rs.getString("name"), rs.getString("desk"),
                        Math.toIntExact(rs.getInt("created")), rs.getString("comment"));
                items[index].setId(String.valueOf(rs.getInt("id")));
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }

    @Override
    public Item[] findByName(String key) {
        Item[] items = null;
        try {
            String sql = quwryProperties.getProperty("numrowswithname"); //находим длинну массива (количество строк с данным именем)
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, key);
            ResultSet rs = prepSt.executeQuery();
            if (rs.next()) {
                items = new Item[rs.getInt("count")];
            }
            sql = quwryProperties.getProperty("findbyname");
            prepSt = conn.prepareStatement(sql);
            prepSt.setString(1, key);
            rs = prepSt.executeQuery();
            int index = 0;
            while (rs.next()) {
                items[index] = new Item(rs.getString("name"), rs.getString("desk"),
                        Math.toIntExact(rs.getInt("created")), rs.getString("comment"));
                items[index].setId(String.valueOf(rs.getInt("id")));
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try {
            String sql = quwryProperties.getProperty("findbyid");
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setInt(1, Integer.parseInt(id));
            ResultSet rs = prepSt.executeQuery();
            if (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("desk"),
                        rs.getInt("created"), rs.getString("comment"));
                item.setId(String.valueOf(rs.getInt("id")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return item;
    }

    @Override
    public void close() throws IOException {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        TrackerSQL trackerSQL = new TrackerSQL();
//        System.out.println(trackerSQL.add(new Item("mouse", "ohrana", 123L, "Мыш не ездит")));
//        trackerSQL.replace("2", new Item("mouууse", "bugh", 123L, "Мышь не ездит"));
//        trackerSQL.delete("1");
//
//        for (int i = 0; i < trackerSQL.findAll().length; i++) {
//            System.out.println(trackerSQL.findAll()[i]);
//        }
//
//        Item[] dd = trackerSQL.findByName("mouse");
//        for (int i = 0; i < dd.length; i++) {
//            System.out.println(dd[i]);
//        }
//
//        System.out.println(trackerSQL.findById("1"));
    }
}
