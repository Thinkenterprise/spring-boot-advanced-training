package com.thinkenterprise;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.thinkenterprise.repository.redis.RedisRouteRepository;

@ComponentScan(basePackageClasses = {Application.class})
@DataRedisTest
public class RedisRouteRepositoryTest {

	@Autowired
	private RedisRouteRepository redisRouteRepository;
	
	@Test
	public void testGetAll() {
		Assert.assertTrue(redisRouteRepository.findAll().size()==3);
	}
	 
}
