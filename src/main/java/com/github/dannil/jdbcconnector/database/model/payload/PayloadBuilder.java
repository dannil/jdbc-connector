package com.github.dannil.jdbcconnector.database.model.payload;

import java.util.Collection;
import java.util.Map;

public class PayloadBuilder {

    private Payload payload;

    public PayloadBuilder() {
        this.payload = new Payload();
    }

    public PayloadBuilder setTable(String table) {
        this.payload.setTable(table);
        return this;
    }

    public PayloadBuilder setType(Type type) {
        this.payload.setType(type);
        return this;
    }

    // Maybe not here??
    public PayloadBuilder setFields(Collection<String> fields) {
        this.payload.setFields(fields);
        return this;
    }

    public PayloadBuilder setWheres(Map<String, String> wheres) {
        this.payload.setWheres(wheres);
        return this;
    }

    public Payload create() {
        return this.payload;
    }

}
