/**
 * "http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos/"
 */

package com.thinkenterprise.springboot.jpa2pc.config;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.thinkenterprise.springboot.jpa2pc.atomikos.AtomikosJtaPlatform;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.thinkenterprise.springboot.jpa2pc.repository.analytics", entityManagerFactoryRef = "analyticsEntityManager", transactionManagerRef = "transactionManager")
public class AnalyticsConfig {



	@Resource(name = "jpaH2VendorAdapter")
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	private Environment env;

	@DependsOn("transactionManager")
	@Bean
	public LocalContainerEntityManagerFactoryBean analyticsEntityManager() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put( "hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName() );
		properties.put( "hibernate.hbm2ddl.auto", env.getProperty( "spring.analytics-datasource.jpa.hibernate.ddl-auto" ) );
		properties.put( "javax.persistence.transactionType", "JTA" );
		properties.put( "hibernate.dialect", env.getProperty( "spring.analytics-datasource.jpa.database-platform" ) );
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource( analyticsDataSource() );
		entityManager.setJpaVendorAdapter( jpaVendorAdapter );
		entityManager.setPackagesToScan( "com.thinkenterprise.springboot.jpa2pc.analytics" );
		entityManager.setPersistenceUnitName( "analyticsPersistenceUnit" );
		entityManager.setJpaPropertyMap( properties );
		return entityManager;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource analyticsDataSource() {
		JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL( env.getProperty( "spring.analytics-datasource.jdbc-url" ) );
		h2XaDataSource.setUser( env.getProperty( "spring.analytics-datasource.username" ) );
		h2XaDataSource.setPassword( env.getProperty( "spring.analytics-datasource.password" ) );
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource( h2XaDataSource );
		xaDataSource.setUniqueResourceName( "analytics-db" );
		return xaDataSource;
	}


}
