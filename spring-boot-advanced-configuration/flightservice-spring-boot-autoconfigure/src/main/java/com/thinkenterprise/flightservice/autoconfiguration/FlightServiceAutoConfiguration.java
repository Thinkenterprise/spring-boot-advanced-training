package com.thinkenterprise.flightservice.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thinkenterprise.flightservice.service.FlightService;
import com.thinkenterprise.flightservice.service.FlightServiceImpl;

@Configuration
@ConditionalOnClass(FlightService.class)
@EnableConfigurationProperties(FlightServiceConfigurationProperties.class)
public class FlightServiceAutoConfiguration {


	@Autowired
	FlightServiceConfigurationProperties fsProperties;
	
	
    @Bean
    @ConditionalOnMissingBean
    public FlightService flightService(){

        FlightService flightService = new FlightServiceImpl();
        flightService.setTaxRate(fsProperties.getTaxRate());
        return flightService;
    }
    
}
