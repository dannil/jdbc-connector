package com.github.dannil.jdbcconnector.database.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private String url;
    private String user;
    private String password;

    // private static final DatabaseConnector INSTANCE = new DatabaseConnector();

    // private static final String URL =
    // "jdbc:sqlserver://localhost:1433;databaseName=master";
    // private static final String USER = "sa";
    // private static final String PASSWORD = "a9b215";

    private DatabaseConnector() {
        // try {
        // DriverManager.registerDriver(new SQLServerDriver());
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
    }

    public DatabaseConnector(String url, String user, String password) {
        this();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void registerDriver(Driver driver) throws SQLException {
        DriverManager.registerDriver(driver);
    }

    // public static Connection getConnection() throws SQLException {
    // return INSTANCE.createConnection();
    // }

    public static void closeResources(Connection connection, Statement statement) throws SQLException {
        connection.close();
        statement.close();
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
