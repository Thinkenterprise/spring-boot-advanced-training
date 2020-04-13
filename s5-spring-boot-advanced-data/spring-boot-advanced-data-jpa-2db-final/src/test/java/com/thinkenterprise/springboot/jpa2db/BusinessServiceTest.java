package com.thinkenterprise.springboot.jpa2db;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.thinkenterprise.springboot.jpa2db.analytics.Tracking;
import com.thinkenterprise.springboot.jpa2db.domain.Flight;
import com.thinkenterprise.springboot.jpa2db.domain.FlightStatus;
import com.thinkenterprise.springboot.jpa2db.repository.analytics.TrackingRepository;
import com.thinkenterprise.springboot.jpa2db.repository.domain.FlightRepository;
import com.thinkenterprise.springboot.jpa2db.service.BusinessService;

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
		Assertions.assertNotNull(flightRepository.findById( flight.getId()));
			
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
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> { service.saveTracking(tracking2); } );
	}


}
