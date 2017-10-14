package com.github.dannil.jdbcconnector.database.model.payload;

public enum Ordering {

    ASCENDING("ASC"), DESCENDING("DESC");

    private String order;

    Ordering(String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }

}
