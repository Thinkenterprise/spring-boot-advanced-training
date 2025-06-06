package com.thinkenterprise.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.thinkenterprise.service.client.RouteService;

@Configuration
public class RouteServiceClientConfiguration {

	@Bean
	public RouteService routeService(WebClient webClient) {
		
		  return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
				                        .build()
		                                .createClient(RouteService.class);	
	
	}
	
}
