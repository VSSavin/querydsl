package com.querydsl.sql.h2;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLSerializer;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.dml.SQLUpdateClause;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Defines an UPDATE for H2 database.
 *
 * @author vssavin
 */
public class H2SQLUpdateClause extends SQLUpdateClause {
    public H2SQLUpdateClause(Connection connection, SQLTemplates templates, RelationalPath<?> entity) {
        super(connection, new Configuration(templates), entity);
    }

    public H2SQLUpdateClause(Connection connection, Configuration configuration, RelationalPath<?> entity) {
        super(connection, configuration, entity);
    }

    public H2SQLUpdateClause(Supplier<Connection> connection, Configuration configuration, RelationalPath<?> entity) {
        super(connection, configuration, entity);
    }

    @Override
    protected SQLSerializer createSerializer() {
        return new H2SqlSerializer(configuration, true);
    }
}
