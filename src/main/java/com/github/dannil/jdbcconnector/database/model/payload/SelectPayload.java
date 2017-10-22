package com.github.dannil.jdbcconnector.database.model.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SelectPayload extends Payload {

    private Collection<String> froms;
    private Map<String, String> wheres;

    private Ordering ordering;

    private SelectPayload() {
        super();
    }

    public SelectPayload(String table) {
        super(table);
        this.froms = new ArrayList<>();
        this.wheres = new LinkedHashMap<>();
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

    @Override
    public Type getType() {
        return Type.SELECT;
    }

    @Override
    public String getQuery() {
        return SelectQueryBuilder.getQuery(this);
    }

}
