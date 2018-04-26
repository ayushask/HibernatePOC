package com.infobeans.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.infobeans.pojo.AccountAnnotated;
import com.infobeans.sessionutils.SessionUtils;

public class AccountDaoForAnnotation {

	public AccountAnnotated get(long accno) {
		Session sc = null;
		AccountAnnotated acc = null;
		try {
			sc = SessionUtils.getSession();
			acc = (AccountAnnotated) sc.get(AccountAnnotated.class, accno);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}

		return acc;
	}

	public void insert(AccountAnnotated acc) {
		Session session = null;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();
			session.save(acc);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(session);
		}
	}
	
	public void update(AccountAnnotated acc) {
		Session session = null;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();
			session.update(acc);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(session);
		}
	}
	
	public void delete(AccountAnnotated acc) {
		Session session = null;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();
			session.delete(acc);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(session);
		}
	}
	
	/*public List<AccountAnnotated> getAccountAnnotatedByName(String name) {
		
		Session ses = null;
		try {
			
		} catch (HibernateException e) {
			
		}
		
		
	}*/
	

}
