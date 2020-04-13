
package com.thinkenterprise.springboot.jpa2pc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * "http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos"
 */
@Configuration
public class H2Config {

	@Bean
	public JpaVendorAdapter jpaH2VendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql( true );
		hibernateJpaVendorAdapter.setGenerateDdl( true );
		hibernateJpaVendorAdapter.setDatabase( Database.H2 );
		return hibernateJpaVendorAdapter;
	}

}
