package com.github.dannil.jdbcconnector.database.model.payload;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SelectQueryBuilder implements QueryBuilder {

    @Override
    public String getQuery(Payload payload) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");

        // Figure out fields (columns) to select from
        Collection<String> froms = payload.getFroms();
        if (!froms.isEmpty()) {
            for (Iterator<String> it = froms.iterator(); it.hasNext();) {
                String field = it.next();
                builder.append(field);
                if (it.hasNext()) {
                    builder.append(", ");
                }
            }
        } else {
            builder.append("*");
        }
        builder.append(" FROM ");
        builder.append(payload.getTable());

        // Check if we have any WHERE conditions
        Map<String, String> wheres = payload.getWheres();
        if (!wheres.isEmpty()) {
            builder.append(" WHERE ");
            for (Iterator<Entry<String, String>> it = wheres.entrySet().iterator(); it.hasNext();) {
                Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                builder.append(key + " = " + value);
                if (it.hasNext()) {
                    builder.append(" AND ");
                }
            }
        }

        // Check if we have any ORDER BY condition
        Ordering ordering = payload.getOrdering();
        if (ordering != null) {
            builder.append(" ORDER BY ");
            builder.append(ordering.getOrder());
        }

        // GET QUERY
        return builder.toString();
    }

}
