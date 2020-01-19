package com.thinkenterprise.actuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.thinkenterprise.service.RouteService;

@Component
@Endpoint(id="routeService")
public class RouteServiceEndpoint {

	@Autowired
	private RouteService routeService;
	
	
	/*
	@ReadOperation
	public String echo(String echo) {
		return echo;
	}
	*/
	
	@ReadOperation
	public Boolean getServiceStatus() {
		return routeService.getServiceStatus();
	}
	
	/*
	@WriteOperation
	public void setRunning(String running) {
		routeService.setRunning(Boolean.parseBoolean(running));
	} 
	
	*/
	

}
