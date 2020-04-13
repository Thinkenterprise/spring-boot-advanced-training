package com.thinkenterprise.springboot.jpa2pc.atomikos;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

/**
 * "http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos"
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {


	private static final long serialVersionUID = 1L;


	public static TransactionManager transactionManager;
	public static UserTransaction transaction;


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UserTransaction locateUserTransaction() {
		return transaction;
	}


}
