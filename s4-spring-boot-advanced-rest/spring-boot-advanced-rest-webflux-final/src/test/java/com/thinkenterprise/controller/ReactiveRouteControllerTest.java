package com.thinkenterprise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.thinkenterprise.domain.route.Route;

@ActiveProfiles("controller")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
public class ReactiveRouteControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void routes() {

		webTestClient.get()
		             .uri("/routes")
		             .exchange()
		             .expectBodyList(Route.class)
		             .hasSize(3);
	}
	
	
	@Test
	public void routesThrowException() {

		webTestClient.get()
		             .uri("/throwException")
		             .exchange()
		             .expectBody(ProblemDetail.class);
		           	            
	}

}
