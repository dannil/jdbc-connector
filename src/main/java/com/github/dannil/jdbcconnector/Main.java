package com.github.dannil.jdbcconnector;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.dannil.jdbcconnector.database.model.payload.Ordering;
import com.github.dannil.jdbcconnector.database.model.payload.SelectPayload;
import com.github.dannil.jdbcconnector.database.model.payload.SelectQueryBuilder;

public class Main {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub

        JdbcConnector con = new JdbcConnector("", "", "");

        List<String> froms = Arrays.asList("column1", "column2");

        Map<String, String> wheres = new LinkedHashMap<String, String>();
        wheres.put("field3", "foo");
        wheres.put("field4", "bar");

        // PayloadBuilder builder = new
        // PayloadBuilder().setTable("table").setType(Type.SELECT).setFroms(fields).setWheres(
        // wheres).setOrdering(Ordering.ASCENDING);
        SelectPayload selectPayload = new SelectPayload("table");
        selectPayload.setFroms(froms);
        selectPayload.setWheres(wheres);
        selectPayload.setOrdering(Ordering.ASCENDING);

        System.out.println(SelectQueryBuilder.getQuery(selectPayload));

        SelectPayload p2 = new SelectPayload("testtable");
        System.out.println(SelectQueryBuilder.getQuery(p2));

        System.out.println(con.select("test"));
    }

}
