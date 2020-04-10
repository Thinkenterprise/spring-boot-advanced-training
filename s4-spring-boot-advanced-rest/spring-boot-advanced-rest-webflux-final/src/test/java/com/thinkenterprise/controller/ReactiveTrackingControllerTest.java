package com.thinkenterprise.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.thinkenterprise.domain.route.Route;

@ActiveProfiles("controller")
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ReactiveTrackingControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void trackings() {
		
		webTestClient.get().uri("/routes").exchange().expectBodyList(Route.class).hasSize(3);
		
	}
	
	
}
