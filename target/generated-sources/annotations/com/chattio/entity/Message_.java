package com.chattio.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Message, Dialog> dialog;
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, String> value;
	public static volatile SingularAttribute<Message, User> user;

}

