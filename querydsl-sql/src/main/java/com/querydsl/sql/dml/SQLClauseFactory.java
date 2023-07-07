package com.querydsl.sql.dml;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.H2Templates;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.h2.H2SQLUpdateClause;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * {@code SQLClauseFactory} is the base factory for creating update, insert, merge, delete clauses
 *
 * @author vssavin
 */
public final class SQLClauseFactory {

    private final Supplier<Connection> connection;
    private final Configuration configuration;

    public SQLClauseFactory(Supplier<Connection> connection, Configuration configuration) {
        this.connection = connection;
        this.configuration = configuration;
    }

    public SQLUpdateClause getUpdateClause(RelationalPath<?> path) {
        if (configuration.getTemplates() instanceof H2Templates) {
            return new H2SQLUpdateClause(connection, configuration, path);
        } else {
            return new SQLUpdateClause(connection, configuration, path);
        }
    }

    public SQLInsertClause getInsertClause(RelationalPath<?> path) {
        return new SQLInsertClause(connection, configuration, path);
    }

    public SQLMergeClause getSQLMergeClause(RelationalPath<?> path) {
        return new SQLMergeClause(connection, configuration, path);
    }

    public SQLDeleteClause getSQLDeleteClause(RelationalPath<?> path) {
        return new SQLDeleteClause(connection, configuration, path);
    }
}
