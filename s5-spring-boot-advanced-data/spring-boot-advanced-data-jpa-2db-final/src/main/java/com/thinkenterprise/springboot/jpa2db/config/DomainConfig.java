package com.thinkenterprise.springboot.jpa2db.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.thinkenterprise.springboot.jpa2db.repository.domain", entityManagerFactoryRef = "domainEntityManager", transactionManagerRef = "domainTransactionManager")
public class DomainConfig {


	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	public PlatformTransactionManager domainTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( domainEntityManager().getObject() );
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean domainEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource( domainDataSource() );
		em.setPackagesToScan( "com.thinkenterprise.springboot.jpa2db.domain" );
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter( vendorAdapter );
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put( "hibernate.hbm2ddl.auto", env.getProperty( "spring.domain-datasource.jpa.hibernate.ddl-auto" ) );
		properties.put( "hibernate.dialect", env.getProperty( "spring.domain-datasource.jpa.database-platform" ) );
		em.setJpaPropertyMap( properties );
		return em;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.domain-datasource")
	public DataSource domainDataSource() {
		return DataSourceBuilder.create().build();
	}

}
