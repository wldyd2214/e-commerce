package com.hhplus.commerce.spring.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.hhplus.commerce.spring.old.domain.user.entity.User;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1409236312L;

    public static final QUser user = new QUser("user");

    public final com.hhplus.commerce.spring.domain.common.model.QBaseEntity _super = new com.hhplus.commerce.spring.domain.common.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> point = createNumber("point", java.math.BigDecimal.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

