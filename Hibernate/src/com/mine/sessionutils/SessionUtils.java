package com.mine.sessionutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {

	private static final ThreadLocal<Session> tlocal = new ThreadLocal<>();
	private static SessionFactory sessionFactory;

	static {
		try {

			if (Thread.currentThread().getName().equals("WithoutAnnotation")) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			} else {
				sessionFactory = new Configuration().configure("hibernate_annot.cfg.xml").buildSessionFactory();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private SessionUtils() {

	}

	public static Session getThreadLocalSession() {
		Session session = (Session) tlocal.get();
		if (session == null) {
			session = sessionFactory.openSession();
			tlocal.set(session);
		}
		return session;
	}

	public static void closeThreadLocalSession() {
		Session session = (Session) tlocal.get();
		tlocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
