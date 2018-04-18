package com.infobeans.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.infobeans.pojo.Account;
import com.infobeans.sessionutils.SessionUtils;

public class AccountDao {

	public Account get(long accno) {
		Session sc = null;
		Account acc = null;
		try {
			sc = SessionUtils.getSession();
			acc = (Account) sc.get(Account.class, accno);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}

		return acc;
	}

	public void insert(Account acc) {
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
	
	public void update(Account acc) {
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
	
	public void delete(Account acc) {
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
	
	/*public List<Account> getAccountByName(String name) {
		
		Session ses = null;
		try {
			
		} catch (HibernateException e) {
			
		}
		
		
	}*/
	

}
