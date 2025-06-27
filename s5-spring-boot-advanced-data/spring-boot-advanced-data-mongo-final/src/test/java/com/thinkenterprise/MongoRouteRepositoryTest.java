package com.thinkenterprise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.thinkenterprise.repository.mongodb.MongoRouteRepository;

@Testcontainers
@SpringBootTest
public class MongoRouteRepositoryTest {

	
	@Container
    @ServiceConnection
    public static MongoDBContainer  mongoDB = new MongoDBContainer(DockerImageName.parse("mongo:5.0"));

	@Autowired
	MongoRouteRepository mongoRouteRepository;
		
	@BeforeEach
    void connectionEstablished() {
		Assertions.assertTrue(mongoDB.isCreated());
		Assertions.assertTrue(mongoDB.isRunning());
    }
		
	@Test
	public void testGetAll() {
		Assertions.assertTrue(mongoRouteRepository.findAll().size()==3);
	}
	 
}
