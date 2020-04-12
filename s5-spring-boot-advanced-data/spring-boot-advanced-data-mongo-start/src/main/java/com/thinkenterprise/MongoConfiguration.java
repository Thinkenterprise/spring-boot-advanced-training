package com.thinkenterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoConfiguration {
	
	@Bean
	public MongoTransactionManager transactionManager(MongoDbFactory mongoDbFactory) {
		return new MongoTransactionManager(mongoDbFactory);
		
	}

}
