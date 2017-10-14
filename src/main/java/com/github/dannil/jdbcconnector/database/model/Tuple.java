package com.github.dannil.jdbcconnector.database.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tuple {

    private Map<String, Object> data;

    public Tuple() {
        this.data = new LinkedHashMap<>();
    }

    public void put(String column, Object value) {
        this.data.put(column, value);
    }

    public Map<String, Object> getData() {
        return this.data;
    }

}
