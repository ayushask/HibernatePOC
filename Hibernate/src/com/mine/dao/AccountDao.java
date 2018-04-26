package com.mine.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.mine.pojo.Account;
import com.mine.sessionutils.SessionUtils;

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
		System.out.println();
		return acc;
	}

	public void insert(Account acc) {
		Session session = null;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();

			// Here the object is in persistant state
			System.out.println("Before calling save");
			session.save(acc);

			System.out.println("After save function --------- You could see no Query called");
			System.out.println(
					"Now object is in persitant state -- hibenate know that this object exist and will update in database");
			System.out.println("-----------------------");
			System.out.println("Current Account details");
			System.out.println("name -- " + acc.getName());
			System.out.println("acc no -- " + acc.getAccno());
			System.out.println("bal -- " + acc.getBalance());
			System.out.println("-----------------------");

			System.out.println("\n\nAny modification in persistant object will automatically will be saved");
			System.out.println("Modifying account name to test2");
			acc.setName("test2");
			System.out.println("Calling commit transaction ---------- Now query will be called");
			session.getTransaction().commit();

			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Calling close session ------ object is now in detached state");
			SessionUtils.closeSession(session);
		}
	}

	public void update(Account acc) {
		Session session = null;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();

			System.out.println("Before calling update");
			session.update(acc);
			System.out.println("After update function --------- You could see no Query called");
			System.out.println("Calling commit transaction ---------- Now query will be called");

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

			System.out.println("Before calling delete");
			session.delete(acc);
			System.out.println(
					"After delete function --------- You could see only select query is called to get current status of object");
			System.out.println("Calling commit transaction ---------- Now delect query query will be called");

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(session);
		}
	}

	/*
	 * public List<Account> getAccountByName(String name) {
	 * 
	 * Session ses = null; try {
	 * 
	 * } catch (HibernateException e) {
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

}
