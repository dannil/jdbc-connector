package com.github.dannil.jdbcconnector.database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.dannil.jdbcconnector.database.connection.DatabaseConnector;
import com.github.dannil.jdbcconnector.database.model.Tuple;
import com.github.dannil.jdbcconnector.database.model.payload.SelectPayload;

public class DataAccess {

    private DatabaseConnector dbConnector;

    private DataAccess() {
        // TODO Auto-generated constructor stub
    }

    public DataAccess(DatabaseConnector dbConnector) {
        this();
        this.dbConnector = dbConnector;
    }

    public List<Tuple> select(String table) throws SQLException {
        SelectPayload payload = new SelectPayload(table);
        return select(table, payload);
        // Connection connection = null;
        // PreparedStatement statement = null;
        // try {
        // connection = this.dbConnector.getConnection();
        // statement = connection.prepareStatement("SELECT * FROM ?");
        // statement.setString(1, table);
        //
        // ResultSet resultSet = statement.executeQuery();
        // return extractResultSet(resultSet);
        // } finally {
        // DatabaseConnector.closeResources(connection, statement);
        // }
    }

    public List<Tuple> select(String table, SelectPayload payload) throws SQLException {
        System.out.println(payload.getQuery());

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.dbConnector.getConnection();
            statement = connection.prepareStatement(payload.getQuery());
            ResultSet resultSet = statement.executeQuery();
            return extractResultSet(resultSet);
        } finally {
            DatabaseConnector.closeResources(connection, statement);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.dbConnector.getConnection();
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } finally {
            DatabaseConnector.closeResources(connection, statement);
        }
    }

    public ResultSet executePreparedQuery(String query, String... parameters) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.dbConnector.getConnection();
            String preparedQuery = String.format(query.replace("?", "%s"), new Object[] { parameters });
            statement = connection.prepareStatement(preparedQuery);
            return statement.executeQuery();
        } finally {
            DatabaseConnector.closeResources(connection, statement);
        }
    }

    private List<Tuple> extractResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Tuple> tuples = new ArrayList<>();
        while (resultSet.next()) {
            Tuple tuple = new Tuple();
            for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
                String column = resultSetMetaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                tuple.put(column, value);
            }
            tuples.add(tuple);
        }
        return tuples;
    }

}
