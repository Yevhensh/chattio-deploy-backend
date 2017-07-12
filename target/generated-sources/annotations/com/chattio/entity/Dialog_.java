package com.chattio.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dialog.class)
public abstract class Dialog_ {

	public static volatile ListAttribute<Dialog, Message> messages;
	public static volatile SingularAttribute<Dialog, Long> id;
	public static volatile SingularAttribute<Dialog, String> dialogName;
	public static volatile ListAttribute<Dialog, User> dialogUsers;

}

