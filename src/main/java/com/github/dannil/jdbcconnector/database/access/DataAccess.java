package com.github.dannil.jdbcconnector.database.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.github.dannil.jdbcconnector.database.connection.DatabaseConnector;
import com.github.dannil.jdbcconnector.database.model.Tuple;
import com.github.dannil.jdbcconnector.database.model.payload.Payload;

public class DataAccess {

    private DatabaseConnector dbConnector;

    private DataAccess() {
        // TODO Auto-generated constructor stub
    }

    public DataAccess(DatabaseConnector dbConnector) {
        this();
        this.dbConnector = dbConnector;
    }

    public List<Tuple> selectAll(String table) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.dbConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM ?");
            statement.setString(1, table);

            ResultSet resultSet = statement.executeQuery();
            return extractResultSet(resultSet);
        } finally {
            DatabaseConnector.closeResources(connection, statement);
        }
    }

    public List<Tuple> selectAll(String table, Payload payload) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.dbConnector.getConnection();

            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ? WHERE ");
            for (Iterator<Entry<String, String>> entries = wheres.entrySet().iterator(); entries.hasNext();) {
                Entry<String, String> entry = entries.next();
                String key = entry.getKey();
                String value = entry.getValue();

                builder.append(key + " = " + value);
                if (entries.hasNext()) {
                    builder.append(" AND ");
                }
            }

            statement = connection.prepareStatement("");
            statement.setString(1, table);

            ResultSet resultSet = statement.executeQuery();
            return extractResultSet(resultSet);
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
