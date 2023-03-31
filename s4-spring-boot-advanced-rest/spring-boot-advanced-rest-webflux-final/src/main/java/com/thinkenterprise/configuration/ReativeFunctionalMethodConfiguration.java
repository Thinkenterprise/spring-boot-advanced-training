package com.thinkenterprise.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.thinkenterprise.handler.RouteEventEmitter;
import com.thinkenterprise.handler.RouteHandler;

@Configuration
@Profile("handler")
public class ReativeFunctionalMethodConfiguration {
	
	@Autowired
	private RouteHandler routeHandler;
	
	@Autowired
	private RouteEventEmitter routeEventEmitter;
	
	@Bean
	public RouterFunction<ServerResponse> routesFunctionMethod() {
		return RouterFunctions.route(RequestPredicates.GET("/routes"), routeHandler::routes);			
	}
	
	@Bean
	public RouterFunction<ServerResponse> startEmitterFunctionMethod() {
		return RouterFunctions.route(RequestPredicates.GET("/startEmitter"), routeEventEmitter::startEmitter);		
	}


}
