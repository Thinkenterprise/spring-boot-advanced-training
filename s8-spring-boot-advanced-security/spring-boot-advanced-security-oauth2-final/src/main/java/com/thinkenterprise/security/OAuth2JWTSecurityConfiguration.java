package com.thinkenterprise.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@Configuration
@EnableMethodSecurity
@Profile("nonReactive")
public class OAuth2JWTSecurityConfiguration {
       
}