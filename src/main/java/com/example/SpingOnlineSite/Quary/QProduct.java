package com.example.SpingOnlineSite.Quary;

import com.example.SpingOnlineSite.Entity.Product;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import javax.annotation.Generated;
import java.math.BigDecimal;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    // Добавьте необходимые поля для фильтрации
    public StringPath name = createString("name");
    public StringPath gender = createString("gender");
    public StringPath category = createString("category");
    public NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QProduct(Class<? extends Product> type, String variable) {
        super(type, variable);
    }

    // ...
}