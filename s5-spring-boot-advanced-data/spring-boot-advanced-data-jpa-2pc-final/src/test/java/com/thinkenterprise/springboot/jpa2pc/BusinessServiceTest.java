package com.thinkenterprise.springboot.jpa2pc;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.UnexpectedRollbackException;

import com.thinkenterprise.springboot.jpa2pc.analytics.Tracking;
import com.thinkenterprise.springboot.jpa2pc.domain.Flight;
import com.thinkenterprise.springboot.jpa2pc.domain.FlightStatus;
import com.thinkenterprise.springboot.jpa2pc.repository.analytics.TrackingRepository;
import com.thinkenterprise.springboot.jpa2pc.repository.domain.FlightRepository;
import com.thinkenterprise.springboot.jpa2pc.service.BusinessService;
import com.thinkenterprise.springboot.jpa2pc.service.BusinessService.Tupel;

@ActiveProfiles("test")
@SpringBootTest
public class BusinessServiceTest {



	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private TrackingRepository trackingRepository;

	@Autowired
	private BusinessService service;


	@Test
	public void testCreateFlight() {
		Flight flight = new Flight( 100L, LocalDate.now() );
		flight = service.saveFlight( flight );
		assertNotNull( flightRepository.findById( flight.getId() ) );
	}

	@Test
	public void testCreateTracking() {
		Tracking tracking = new Tracking( LocalDateTime.now(), 343L, FlightStatus.SCHEDULED );
		tracking = service.saveTracking( tracking );
		Assertions.assertNotNull(trackingRepository.findById( tracking.getId()));	
	}

	@Test
	public void testCreateDuplicateTracking() {
		Tracking tracking = new Tracking( LocalDateTime.now(), 343L, FlightStatus.SCHEDULED );
		Tracking tracking2 = new Tracking( tracking );
		tracking = service.saveTracking( tracking );
		Assertions.assertNotNull(trackingRepository.findById( tracking.getId()));
		Assertions.assertThrows(UnexpectedRollbackException.class, () -> { service.saveTracking(tracking2); } );
	}

	@Test
	public void testCreateFlightTracking() {
		Flight flight = new Flight( 100L, LocalDate.now() );
		Tracking tracking = new Tracking( LocalDateTime.now(), 343L, FlightStatus.SCHEDULED );
		Tupel tupel = service.saveTupel( flight, tracking );
		Assertions.assertNotNull(flightRepository.findById( tupel.flight.getId()));
		Assertions.assertNotNull(trackingRepository.findById( tupel.tracking.getId()));
	}

	@Test
	public void testCreateFlightDuplicateTracking() {
		Flight flight = new Flight( 100L, LocalDate.now() );
		Tracking tracking = new Tracking( LocalDateTime.now(), 752L, FlightStatus.SCHEDULED );
		Tracking tracking2 = new Tracking( tracking );
		tracking = service.saveTracking( tracking );
		Assertions.assertThrows(UnexpectedRollbackException.class, () -> { service.saveTupel( flight, tracking2 ); } );
	}


}
