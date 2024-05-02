package com.thinkenterprise.controller;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;

@RestController
public class RouteController {

	private static final Log logger = LogFactory.getLog(RouteController.class);
	
	private Random random = new Random();

	private ObservationRegistry observationRegistry;
	
	
	public RouteController(ObservationRegistry observationRegistry) {
		this.observationRegistry=observationRegistry;
	}
	
	
	@RequestMapping("trace")
	public String getTrace() {
		logger.info("Hello Tracing");
		return "Hello Tracing";
	}
	
	
	@RequestMapping("observability")
	@Observed(name = "observability", contextualName = "observability", lowCardinalityKeyValues = {"A", "B"})
	public String getObservability() {
		return "Hello Tracing";
	}

	
	@RequestMapping("observabilityApi")
	public String getObservabilityApi() {
		
		Integer randomValue = random.nextInt(1000);
		
		Observation businessFunctionObservation= Observation.createNotStarted("businessFunctionTimerObservation", this.observationRegistry);
		
		businessFunctionObservation.start();
		
		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		businessFunctionObservation.stop();
		
		return "Hello Tracing";
	}
	

	
	
	
	
	
	
	
}
