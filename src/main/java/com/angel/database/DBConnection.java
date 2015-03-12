package com.angel.database;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {
	private static SessionFactory sessionFactory = null;
	private static Logger logger = Logger.getLogger(DBConnection.class);

	private static SessionFactory buildSessionFactory() {

		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
