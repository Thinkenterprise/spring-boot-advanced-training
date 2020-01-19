package com.thinkenterprise.actuator.endpoint.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.thinkenterprise.service.RouteService;

import reactor.core.publisher.Mono;

@Component
@Endpoint(id="reactiveRouteService")
public class ReactiveRouteServiceEndpoint {

	@Autowired
	private RouteService routeService;
	
	
	/*
	@ReadOperation
	public String echo(String echo) {
		return echo;
	}
	*/
	
	
	@ReadOperation
	public Mono<Boolean> getServiceStatus() {
		return Mono.just(routeService.getServiceStatus());
	}
	
	
	/*
	@WriteOperation
	public void setRunning(String running) {
		routeService.setRunning(Boolean.parseBoolean(running));
	} 
	
	*/
	

}
