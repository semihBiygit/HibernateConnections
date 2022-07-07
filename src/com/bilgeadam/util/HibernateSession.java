package com.bilgeadam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.entity.Role;
import com.bilgeadam.entity.Rule;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;

public class HibernateSession {

	private static final SessionFactory sessionFactory = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {
		SessionFactory factory = null;
		try {
			Configuration config = new Configuration(); // creating instance

			config.addAnnotatedClass(User.class); 
			config.addAnnotatedClass(UserDetail.class); 
			config.addAnnotatedClass(Role.class); 
			config.addAnnotatedClass(Rule.class); 

			factory = config.configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
