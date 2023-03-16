package com.thinkenterprise;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HttpExchange("routes")
public interface RouteService {
	
	@GetExchange
	Flux<Route> routes();
	
	@GetExchange("{id}")
	Mono<Route> routes(@PathVariable(name = "id") Long id);

}
