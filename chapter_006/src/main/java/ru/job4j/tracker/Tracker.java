package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

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

    private final String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    private final String login = "postgres";
    private final String password = "kozemir";
    private Connection conn = null;

    public Tracker() {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sqlCommand = "create table tracker(id serial primary key, name varchar(200), out_desk varchar(200), created timestamp, comment varchar(2000))";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("new table succesful created");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResourse();
        }
    }

    public void add(Item item) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "insert into tracker(name, out_desk, created, comment) values(?, ?, ?, ?)";
            PreparedStatement prepSt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepSt.setString(1, item.getName());
            prepSt.setString(2, item.getDesk());
            prepSt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            prepSt.setString(4, item.getComments());
            prepSt.executeUpdate();
            ResultSet generatedKeys = prepSt.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Item added, id = " + generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResourse();
        }
    }

    public void update(int id, Item item) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "update tracker set name = ?, out_desk = ?, comment = ? where id = ?";
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
            closeResourse();
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
            closeResourse();
        }
    }

    public void findAll() {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "select * from tracker";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desk = rs.getString("out_desk");
                Timestamp created = rs.getTimestamp("created");
                String comment = rs.getString("comment");
                System.out.printf("%d %s %s %tF %tT %s \n", id, name, desk, created, created, comment);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResourse();
        }
    }

    public void findById(int idOfItem) {
        try {
            conn = DriverManager.getConnection(url, login, password);
            String sql = "select * from tracker where id = ?";
            PreparedStatement prepSt = conn.prepareStatement(sql);
            prepSt.setInt(1, idOfItem);
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desk = rs.getString("out_desk");
                Timestamp created = rs.getTimestamp("created");
                String comment = rs.getString("comment");
                System.out.printf("%d %s %s %tF %tT %s \n", id, name, desk, created, created, comment);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResourse();
        }
    }

    @Override
    public void closeResourse() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

//    public static void main(String[] args) {
//        Tracker tracker = new Tracker();
//        tracker.add(new Item("printer", "otk", "принтер не печатает"));
//        tracker.add(new Item("mouse", "buhg", "мыш не ездит"));
//        tracker.update(2, new Item("mouse", "bujjjhg", "мышь не ездит"));
//        tracker.update(2, new Item("mouse", "вохр", "тоже не ездит"));
//        tracker.delete(3);
//        tracker.delete(4);
//    }
}
