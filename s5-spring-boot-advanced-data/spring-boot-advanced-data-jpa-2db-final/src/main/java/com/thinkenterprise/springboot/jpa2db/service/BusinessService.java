package com.thinkenterprise.springboot.jpa2db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkenterprise.springboot.jpa2db.analytics.Tracking;
import com.thinkenterprise.springboot.jpa2db.domain.Flight;
import com.thinkenterprise.springboot.jpa2db.repository.analytics.TrackingRepository;
import com.thinkenterprise.springboot.jpa2db.repository.domain.FlightRepository;

@Service
public class BusinessService {


	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private TrackingRepository trackingRepository;


	@Transactional(transactionManager = "domainTransactionManager")
	public Flight saveFlight( Flight flight ) {
		return flightRepository.save( flight );
	}

	@Transactional(transactionManager = "analyticsTransactionManager")
	public Tracking saveTracking( Tracking tracking ) {
		return trackingRepository.save( tracking );
	}


}
