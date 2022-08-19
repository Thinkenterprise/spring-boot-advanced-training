package com.thinkenterprise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.ComponentScan;

import com.thinkenterprise.repository.redis.RedisRouteRepository;

@ComponentScan(basePackageClasses = { Application.class })
@DataRedisTest
public class RedisRouteRepositoryTest {

	@Autowired
	private RedisRouteRepository redisRouteRepository;

	@Test
	public void testGetAll() {
		assertEquals(3, redisRouteRepository.findAll().size());
	}

}
