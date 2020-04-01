package com.thinkenterprise;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.thinkenterprise.repository.jpa.JpaRouteRepository;
import com.thinkenterprise.repository.jpa.RouteRepository;

@ComponentScan(basePackageClasses = {RouteRepository.class})
@DataJpaTest
public class JpaRouteRepositoryTest {

	@Autowired
	private JpaRouteRepository jdbcRouteRepository;
	
	@Test
	public void testGetAll() {
		Assert.assertTrue(jdbcRouteRepository.findAll().size()==3);
	}
	 
}
