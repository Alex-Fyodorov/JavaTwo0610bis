package lesson10;

import java.sql.*;
import java.util.Random;

public class JdbcApp {

    private static Connection connection;
    private static Statement statement;
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            connect();
            createTable();
            long start = System.currentTimeMillis();
            insertStudents();
            System.out.println("insert stmt " +
                    (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            insertStudentsBatch();
            System.out.println("insert stmt " +
                    (System.currentTimeMillis() - start) + "ms");
            insertOneStudent("Bill", "74");
            readData();
            dropTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        statement = connection.createStatement();
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists students (\n" +
                "id integer primary key autoincrement not null,\n" +
                "name text not null,\n" +
                "group_name text,\n" +
                "score integer\n" +
                ");");
    }

    /**
     * В данном методе сначала выключается, а потом включается автокоммит,
     * чтобы все действия записались сразу (транзакция). Здесь это сделано
     * просто для демонстрации.
     */
    private static void insertStudents() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10; i++) {
            statement.executeUpdate(
                    "insert into students (name, group_name, score) values " +
                            "('Bob" + i + "', '22', 3)");

        }
        connection.commit();
        connection.setAutoCommit(true);
    }

    /**
     * Безопасная вставка в таблицу. Небезопасная закомментирована.
     */
    private static void insertOneStudent(String name, String group) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score) values " +
                        "(?, ?, 3)")) {
            ps.setString(1, name);
            ps.setString(2, group);
            ps.execute();
        } catch (SQLException sq) {
            sq.printStackTrace();
        }

 /*       statement.executeUpdate(
                "insert into students (name, group_name, score) values " +
                        "('" + name + "', '22', 3)");*/
    }

    /**
     * Пакетная вставка в таблицу. При больших вставляемых объёмах она
     * осуществляется гораздо быстрее.
     */
    private static void insertStudentsBatch() {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score) " +
                "values (?, ?, ?)")) {
            for (int i = 0; i < 10; i++) {
                ps.setString(1, "Jack" + i);
                ps.setString(2, "group-" + (10 - i));
                ps.setInt(3, 1 + random.nextInt(5));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
    }

    private static void readData() {
        try (ResultSet rs = statement.executeQuery("select * from students")){
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " +
                        rs.getString("name") + " " +
                        rs.getString(3) + " " +
                        rs.getInt("score"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void dropTable() {
        try {
            statement.executeUpdate("drop table students");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
