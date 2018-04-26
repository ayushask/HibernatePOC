package com.mine.service;

import java.util.Date;
import java.util.Random;

import com.mine.dao.AccountDao;
import com.mine.pojo.Account;
import com.mine.sessionutils.SessionUtils;

public class AccountService {

	public final static boolean toDelete = true;
	
	public static void main(String[] args) {
		
		Thread.currentThread().setName("WithoutAnnotation");
		AccountDao dao = new  AccountDao();
		int accno = new Random().nextInt(10000);
		
		System.out.println("=======================================================================");
		System.out.println("Creating new Account Object --  here hibernate not aware that object exist");
		// Here the object is in transient state
		Account createAcc = new Account();
		createAcc.setAccno(accno);
		createAcc.setBalance(12000);
		createAcc.setCreationDate(new Date());
		createAcc.setName("Test");
		System.out.println("Here Object is in transient state");
		dao.insert(createAcc);
		System.out.println("-----------------------");
		System.out.println("Account details");
		System.out.println("name -- " + createAcc.getName());
		System.out.println("acc no -- " + createAcc.getAccno());
		System.out.println("bal -- " + createAcc.getBalance());
		System.out.println("-----------------------");
		System.out.println("=======================================================================");
		
		
		Account updateAcc = new Account();
		updateAcc.setAccno(accno);
		updateAcc.setBalance(12);
		updateAcc.setCreationDate(new Date());
		updateAcc.setName("Test123");
		dao.update(updateAcc);
		System.out.println("Account Updated successfully");
		
		
		Account retriveAcc = dao.get(accno);
		System.out.println("Account details --- ");
		System.out.println("name -- " + retriveAcc.getName());
		System.out.println("acc no -- " + retriveAcc.getAccno() );
		System.out.println("bal -- " + retriveAcc.getBalance());
		System.out.println("-----------------------");
		
		if(toDelete) {
			dao.delete(retriveAcc);
			dao.delete(updateAcc);
			dao.delete(createAcc);
		}
		
		SessionUtils.closeSessionFactory();
		
	}
	
}
