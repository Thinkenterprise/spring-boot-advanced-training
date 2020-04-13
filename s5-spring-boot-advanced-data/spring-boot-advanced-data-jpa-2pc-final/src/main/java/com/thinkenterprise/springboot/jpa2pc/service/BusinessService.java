

package com.thinkenterprise.springboot.jpa2pc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkenterprise.springboot.jpa2pc.analytics.Tracking;
import com.thinkenterprise.springboot.jpa2pc.domain.Flight;
import com.thinkenterprise.springboot.jpa2pc.repository.analytics.TrackingRepository;
import com.thinkenterprise.springboot.jpa2pc.repository.domain.FlightRepository;

@Service
public class BusinessService {
	

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private TrackingRepository trackingRepository;



	@Transactional
	public Flight saveFlight( Flight flight ) {
		return flightRepository.save( flight );
	}

	@Transactional
	public Tracking saveTracking( Tracking tracking ) {
		return trackingRepository.save( tracking );
	}

	@Transactional
	public Tupel saveTupel( Flight flight, Tracking tracking ) {
		flight = flightRepository.save( flight );
		tracking.setFlightId( flight.getId() );
		tracking = trackingRepository.save( tracking );
		return new Tupel( flight, tracking );
	}


	public static class Tupel {

		public Flight flight;
		public Tracking tracking;

		Tupel( Flight flight, Tracking tracking ) {
			Tupel.this.flight = flight;
			Tupel.this.tracking = tracking;
		}
	}

}
