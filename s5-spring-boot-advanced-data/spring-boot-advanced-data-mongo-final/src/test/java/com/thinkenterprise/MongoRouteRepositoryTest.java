package com.thinkenterprise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.ComponentScan;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.thinkenterprise.repository.mongodb.MongoRouteRepository;

@Testcontainers
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DataMongoTest
@ComponentScan(basePackageClasses = MongoRouteRepository.class)
public class MongoRouteRepositoryTest {

	
	@Container
    //@ServiceConnection
    MongoDBContainer mongoDB = new MongoDBContainer(DockerImageName.parse("mongo:5.0"));

	@BeforeEach
    void connectionEstablished() {
		Assertions.assertTrue(mongoDB.isCreated());
		Assertions.assertTrue(mongoDB.isRunning());
    }
	
	
	@Autowired
	MongoRouteRepository mongoRouteRepository;
	
	@Test
	public void testGetAll() {
		Assertions.assertTrue(mongoRouteRepository.findAll().size()==3);
	}
	 
}
