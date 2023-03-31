package com.thinkenterprise.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;

@Configuration
@Profile("functional")
public class ReativeFunctionalConfiguration {
	
	@Bean
	public RouterFunction<ServerResponse> routesRouterFunction() {
	
		return RouterFunctions.route(RequestPredicates.GET("/routes"), 
				                     request -> ServerResponse.ok().body(Flux.just(new Route("LH7902","MUC","IAH"), 
				    				         									   new Route("LH1602","MUC","IBZ"), 
				    				         									   new Route("LH401","FRA","NYC")), 
				                     Route.class));
				
	}


}
