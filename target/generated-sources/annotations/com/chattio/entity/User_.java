package com.chattio.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile ListAttribute<User, Message> messages;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile ListAttribute<User, Dialog> dialogs;

}

