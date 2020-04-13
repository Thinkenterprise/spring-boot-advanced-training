
package com.thinkenterprise.springboot.jpa2db.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.thinkenterprise.springboot.jpa2db.repository.analytics", entityManagerFactoryRef = "analyticsEntityManager", transactionManagerRef = "analyticsTransactionManager")
public class AnalyticsConfig {


	@Autowired
	private Environment env;
	
	@Bean
	public PlatformTransactionManager analyticsTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( analyticsEntityManager().getObject() );
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean analyticsEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource( analyticsDataSource() );
		em.setPackagesToScan( "com.thinkenterprise.springboot.jpa2db.analytics" );
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter( vendorAdapter );
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put( "hibernate.hbm2ddl.auto", env.getProperty( "spring.analytics-datasource.jpa.hibernate.ddl-auto" ) );
		properties.put( "hibernate.dialect", env.getProperty( "spring.analytics-datasource.jpa.database-platform" ) );
		em.setJpaPropertyMap( properties );
		return em;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.analytics-datasource")
	public DataSource analyticsDataSource() {
		return DataSourceBuilder.create().build();
	}

}
