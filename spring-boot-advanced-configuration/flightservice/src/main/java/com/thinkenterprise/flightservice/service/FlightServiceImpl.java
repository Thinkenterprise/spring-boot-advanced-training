package com.thinkenterprise.flightservice.service;

import java.util.List;

import com.thinkenterprise.flightservice.model.FlightPrice;

public class FlightServiceImpl implements FlightService {

	private float taxRate = 1.0f;
	
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	
	public double totalPrice(List<FlightPrice> flightPrices) {
		double totalPrice = 0.0;
		for(FlightPrice flightPrice: flightPrices) {
			totalPrice = totalPrice + flightPrice.getPrice();
		}
		
		return totalPrice * taxRate;	
	}

}
