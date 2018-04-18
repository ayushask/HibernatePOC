package com.infobeans.service;

import java.util.Date;
import java.util.Random;

import com.infobeans.dao.AccountDao;
import com.infobeans.pojo.Account;
import com.infobeans.sessionutils.SessionUtils;

public class AccountService {

	public final static boolean toDelete = true;
	
	public static void main(String[] args) {
		AccountDao dao = new  AccountDao();
		
		int accno = new Random().nextInt(10000);
		
		
		Account createAcc = new Account();
		createAcc.setAccno(accno);
		createAcc.setBalance(12000);
		createAcc.setCreationDate(new Date());
		createAcc.setName("Test");
		dao.insert(createAcc);
		System.out.println("Account Created successfully  -- accno "+accno);
		
		
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
