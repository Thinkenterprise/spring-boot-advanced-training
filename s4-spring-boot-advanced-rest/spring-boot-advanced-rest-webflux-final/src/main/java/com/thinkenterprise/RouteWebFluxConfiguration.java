package com.thinkenterprise;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class RouteWebFluxConfiguration implements WebFluxConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		WebFluxConfigurer.super.addCorsMappings(registry);
	}

}
