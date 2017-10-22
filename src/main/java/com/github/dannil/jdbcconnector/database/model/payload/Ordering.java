package com.github.dannil.jdbcconnector.database.model.payload;

public enum Ordering {

    ASCENDING("asc"), DESCENDING("desc");

    private String order;

    Ordering(String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }

}
