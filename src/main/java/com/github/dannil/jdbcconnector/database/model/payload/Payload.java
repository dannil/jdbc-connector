package com.github.dannil.jdbcconnector.database.model.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Payload {

    private Type type;
    private String table;

    private Collection<String> froms;
    private Map<String, String> wheres;

    private Ordering ordering;

    public Payload() {
        this.froms = new ArrayList<>();
        this.wheres = new LinkedHashMap<>();
    }

    public Payload(String table, Type type) {
        this();
        this.table = table;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Collection<String> getFroms() {
        return froms;
    }

    public void setFroms(Collection<String> froms) {
        this.froms = froms;
    }

    public Map<String, String> getWheres() {
        return wheres;
    }

    public void setWheres(Map<String, String> wheres) {
        this.wheres = wheres;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public String getQuery() {
        switch (this.type) {
            case SELECT:
                return new SelectQueryBuilder().getQuery(this);
            default:
                return null;

        }
    }

}
