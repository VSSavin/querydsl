package com.mysema.query.types.quant;

import com.mysema.query.types.CollectionType;
import com.mysema.query.types.expr.EBoolean;
import com.mysema.query.types.expr.Expr;
import com.mysema.query.types.operation.Ops.Op;

/**
 * The Class Boolean.
 */
public class QBoolean<Q> extends EBoolean implements Quant{
    private final Expr<?> col;
    private final Op<?> op;
    public QBoolean(Op<?> op, CollectionType<Q> col) {
        this.op = op;
        this.col = (Expr<?>) col;
    }
    public Op<?> getOperator() {return op;}
    public Expr<?> getTarget() {return col;}                           
}