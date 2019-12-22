package com.thinkenterprise.flightservice.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "flightservice")
public class FlightServiceConfigurationProperties {

	
	private float taxRate;

	public float getTaxRate() {
		return this.taxRate;
	}
	
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	
}
