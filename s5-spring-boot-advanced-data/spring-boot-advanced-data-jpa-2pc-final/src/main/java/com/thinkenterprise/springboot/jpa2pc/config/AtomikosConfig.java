

package com.thinkenterprise.springboot.jpa2pc.config;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.thinkenterprise.springboot.jpa2pc.atomikos.AtomikosJtaPlatform;

/**
 * "http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos"
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class AtomikosConfig {

	

	@Bean
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransaction = new UserTransactionImp();
		userTransaction.setTransactionTimeout( 10000 );
		AtomikosJtaPlatform.transaction = userTransaction;
		return userTransaction;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown( false );
		AtomikosJtaPlatform.transactionManager = userTransactionManager;
		return userTransactionManager;
	}

	@Bean
	@DependsOn({
			"userTransaction", "atomikosTransactionManager"
	})
	public PlatformTransactionManager transactionManager() throws Throwable {
		UserTransaction userTransaction = userTransaction();
		AtomikosJtaPlatform.transaction = userTransaction;
		TransactionManager atomikosTransactionManager = atomikosTransactionManager();
		return new JtaTransactionManager( userTransaction, atomikosTransactionManager );
	}

	
}
