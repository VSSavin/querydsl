package com.querydsl.sql.h2;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.*;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

import static org.junit.Assert.*;

/**
 * @author vssavin on 03.07.2023
 */
public class QEventH2Test {

    private DataSource dataSource;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        dataSource = new JdbcDataSource();

        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:./target/h2-gen";
        Connection conn = DriverManager.getConnection(url, "sa", "");
        Statement stmt = conn.createStatement();

        ((JdbcDataSource)dataSource).setURL(url);
        ((JdbcDataSource)dataSource).setUser("sa");
        ((JdbcDataSource)dataSource).setPassword("");

        stmt.execute("drop table EVENT if exists");
        stmt.execute("create table EVENT(" +
                "ID int AUTO_INCREMENT PRIMARY KEY, " +
                "NAME varchar(30), DATE timestamp not null)");

        try {
            stmt.close();
        } finally {
            conn.close();
        }
    }

    @Test
    public void test() throws SQLException {
        QEvent entity = QEvent.QEvent;
        RelationalPathBase<QEvent> relationalPathBase =
                new RelationalPathBase<>(entity.getType(), entity.getMetadata(), "", "EVENT");

        String insertName = "Event number one";

        AbstractSQLQueryFactory<?> queryFactory = new SQLQueryFactory(new Configuration(new H2Templates()), dataSource);
        queryFactory.insert(relationalPathBase).columns(entity.name, entity.dateTimeStamp)
                .values(insertName, new java.util.Date()).execute();

        Tuple inserted = queryFactory.select(entity.name, entity.dateTimeStamp).from(entity)
                .where(entity.id.eq(1)).fetchOne();

        assertNotNull(inserted);
        assertEquals(inserted.get(entity.name), insertName);

        String updateName = "Event number two";

        queryFactory.update(relationalPathBase).where(entity.id.eq(1))
                .set(entity.name, Expressions.stringPath(updateName))
                .set(entity.dateTimeStamp, Expressions.dateTimePath(Timestamp.class, new Timestamp(System.currentTimeMillis()).toString()))
                .execute();


        Tuple updated = queryFactory.select(entity.name, entity.dateTimeStamp).from(entity)
                .where(entity.id.eq(1)).fetchOne();

        assertNotNull(updated);
        assertEquals(updated.get(entity.name), updateName);

    }
}
