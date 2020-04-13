package com.thinkenterprise.springboot.jpa2db.config;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.thinkenterprise.springboot.jpa2db.analytics.Tracking;
import com.thinkenterprise.springboot.jpa2db.domain.FlightStatus;
import com.thinkenterprise.springboot.jpa2db.repository.analytics.TrackingRepository;

@Configuration
@EnableScheduling
@Profile("!test")
public class SchedulerConfig {


	private static final Logger LOGGER = LoggerFactory.getLogger( SchedulerConfig.class );
	private static Tracking tracking = new Tracking( LocalDateTime.now(), 343L, FlightStatus.SCHEDULED );


	@Autowired
	private TrackingRepository trackingRepository;

	@Scheduled(initialDelay = 500, fixedDelay = 15000)
	public void schedule() {
		LOGGER.info( "Scheduled: Alive as of [" + new Date().toString() + "]!" );
		Tracking tracking2 = new Tracking( tracking );
		LOGGER.info( "Saving tracking <" + tracking2 + ">..." );
		tracking2 = trackingRepository.save( tracking2 );
		LOGGER.info( "Done saving tracking <" + tracking2 + ">!" );
	}


}
