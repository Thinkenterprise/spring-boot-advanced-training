package com.thinkenterprise;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
	private JpaRouteRepository jpaRouteRepository;
	
	@Test
	public void testGetAll() {
		assertTrue(jpaRouteRepository.findAll().size()==3);
	}
	 
}
