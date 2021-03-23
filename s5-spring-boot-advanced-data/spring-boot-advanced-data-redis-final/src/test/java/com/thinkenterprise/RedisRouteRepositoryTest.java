package com.thinkenterprise;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thinkenterprise.repository.redis.RedisRouteRepository;

//@ComponentScan(basePackageClasses = {Application.class})
//@DataRedisTest
public class RedisRouteRepositoryTest {

	//@Autowired
	private RedisRouteRepository redisRouteRepository;
	
	//@Test
	public void testGetAll() {
		assertTrue(redisRouteRepository.findAll().size()==3);
	}
	 
}
