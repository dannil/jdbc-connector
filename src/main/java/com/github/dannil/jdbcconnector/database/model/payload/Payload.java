package com.github.dannil.jdbcconnector.database.model.payload;

public abstract class Payload {

    // private Type type;
    private String table;

    protected Payload() {

    }

    public Payload(String table/* , Type type */) {
        this();
        this.table = table;
        // this.type = type;
    }

    public abstract Type getType();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public abstract String getQuery();

    @Override
    public String toString() {
        return getQuery();
    }

}
