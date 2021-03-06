package com.github.dannil.jdbcconnector;

import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.github.dannil.jdbcconnector.database.access.DataAccess;
import com.github.dannil.jdbcconnector.database.connection.DatabaseConnector;
import com.github.dannil.jdbcconnector.database.model.Tuple;
import com.github.dannil.jdbcconnector.database.model.payload.SelectPayload;

public class JdbcConnector {

    private DatabaseConnector dbConnector;

    private DataAccess dataAccess;

    private JdbcConnector() {

    }

    public JdbcConnector(String url, String user, String password) {
        this();
        this.dbConnector = new DatabaseConnector(url, user, password);
        this.dataAccess = new DataAccess(this.dbConnector);
    }

    public void registerDriver(Driver driver) throws SQLException {
        this.dbConnector.registerDriver(driver);
    }

    public List<Tuple> select(String table) throws SQLException {
        return this.dataAccess.select(table);
    }

    public List<Tuple> select(String table, SelectPayload payload) throws SQLException {
        return this.dataAccess.select(table, payload);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return this.dataAccess.executeQuery(query);
    }

    public ResultSet executePreparedQuery(String query, String... parameters) throws SQLException {
        return this.dataAccess.executePreparedQuery(query, parameters);
    }

}
