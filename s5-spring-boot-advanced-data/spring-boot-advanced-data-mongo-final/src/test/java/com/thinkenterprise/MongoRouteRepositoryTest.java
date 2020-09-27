package com.thinkenterprise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import com.thinkenterprise.repository.mongodb.MongoRouteRepository;

@ComponentScan(basePackageClasses = {Application.class})
@DataMongoTest
public class MongoRouteRepositoryTest {

	@Autowired
	private MongoRouteRepository mongoRouteRepository;
	
	@Test
	public void testGetAll() {
		Assertions.assertTrue(mongoRouteRepository.findAll().size()==3);
	}
	 
}
