package com.github.dannil.jdbcconnector;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.dannil.jdbcconnector.database.model.payload.Ordering;
import com.github.dannil.jdbcconnector.database.model.payload.Payload;
import com.github.dannil.jdbcconnector.database.model.payload.PayloadBuilder;
import com.github.dannil.jdbcconnector.database.model.payload.SelectQueryBuilder;
import com.github.dannil.jdbcconnector.database.model.payload.Type;

public class Main {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub

        JdbcConnector con = new JdbcConnector("", "", "");

        List<String> fields = Arrays.asList("field1", "field2");

        Map<String, String> wheres = new LinkedHashMap<String, String>();
        wheres.put("field3", "foo");
        wheres.put("field4", "bar");

        PayloadBuilder builder = new PayloadBuilder().setTable("table").setType(Type.SELECT).setFroms(fields).setWheres(
                wheres).setOrdering(Ordering.ASCENDING);
        Payload payload = builder.create();

        SelectQueryBuilder queryBuilder = new SelectQueryBuilder();
        System.out.println(queryBuilder.getQuery(payload));

        Payload p2 = new Payload();
        System.out.println(queryBuilder.getQuery(p2));

        System.out.println(con.selectAll("test"));
    }

}
