package com.thinkenterprise.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Profile("nonReactive")
public class OAuth2JWTSecurityConfiguration extends GlobalMethodSecurityConfiguration  {
       
}