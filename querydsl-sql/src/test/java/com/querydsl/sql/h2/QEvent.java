package com.querydsl.sql.h2;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.querydsl.sql.RelationalPathBase;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vssavin on 03.07.2023
 */
public class QEvent extends RelationalPathBase<QEvent> implements Serializable {

    private static final long serialVersionUID = -5505264055225775023L;

    public static final QEvent QEvent = new QEvent("EVENT");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<Date> dateTimeStamp = createDateTime("date", Date.class);

    public QEvent(String variable) {
        super(QEvent.class, PathMetadataFactory.forVariable(variable), "", "EVENT");
        addMetadata();
    }

    public QEvent(BeanPath<? extends QEvent> entity) {
        super(entity.getType(), entity.getMetadata(), "", "EVENT");
        addMetadata();
    }

    public QEvent(PathMetadata metadata) {
        super(QEvent.class, metadata, "", "EVENT");
        addMetadata();
    }

    protected void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID"));
        addMetadata(name, ColumnMetadata.named("NAME"));
        addMetadata(dateTimeStamp, ColumnMetadata.named("DATE"));
    }
}
