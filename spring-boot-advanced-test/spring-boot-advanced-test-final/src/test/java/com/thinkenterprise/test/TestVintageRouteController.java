package com.thinkenterprise.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinkenterprise.domain.route.Route;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestVintageRouteController {
	
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	@Test
	public void checkRouteRepositoryWithAutowire() {
		System.out.println("");
		Iterable<Route> iterable = testRestTemplate.getForObject("/routes", Iterable.class);
		Assertions.assertTrue(iterable.iterator().hasNext());
	} 

}
