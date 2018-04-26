package com.mine.service;

import java.util.Date;
import java.util.Random;

import com.mine.dao.AccountDaoForAnnotation;
import com.mine.pojo.AccountAnnotated;
import com.mine.sessionutils.SessionUtils;

public class AccountServiceForAnnotation {

	public final static boolean toDelete = false;

	public static void main(String[] args) {
		
		Thread.currentThread().setName("WithAnnotation");
		AccountDaoForAnnotation dao = new AccountDaoForAnnotation();
		int accno = new Random().nextInt(10000);

		System.out.println("=======================================================================");
		System.out.println("Creating new Account Object");
		AccountAnnotated createAcc = new AccountAnnotated();
		createAcc.setAccno(accno);
		createAcc.setBalance(12000);
		createAcc.setCreationDate(new Date());
		createAcc.setName("Test");
		System.out.println("Here Object is in transient state");
		dao.insert(createAcc);
		System.out.println("AccountAnnotated Created successfully  -- accno " + accno);
		System.out.println("=======================================================================");
		
		
		AccountAnnotated updateAcc = new AccountAnnotated();
		updateAcc.setAccno(accno);
		updateAcc.setBalance(12);
		updateAcc.setCreationDate(new Date());
		updateAcc.setName("Test123");
		dao.update(updateAcc);
		System.out.println("AccountAnnotated Updated successfully");

		AccountAnnotated retriveAcc = dao.get(accno);
		System.out.println("AccountAnnotated details --- ");
		System.out.println("name -- " + retriveAcc.getName());
		System.out.println("acc no -- " + retriveAcc.getAccno());
		System.out.println("bal -- " + retriveAcc.getBalance());
		System.out.println("-----------------------");

		if (toDelete) {
			dao.delete(retriveAcc);
			dao.delete(updateAcc);
			dao.delete(createAcc);
		}

		SessionUtils.closeSessionFactory();

	}

}
