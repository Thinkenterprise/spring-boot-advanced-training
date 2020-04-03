package com.thinkenterprise.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;


@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class ReactiveOAuth2JWTSecurityConfiguration  {
	
      
}