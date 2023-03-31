/*
 * Copyright (C) 2019 Thinkenterprise
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * @author Michael Schaefer
 */
package com.thinkenterprise.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Service
public class RouteServiceWithObservationAPI {

	private boolean serviceStatus;

	private Random random = new Random();

	// private MeterRegistry meterRegistry;
	private ObservationRegistry observationRegistry;

	// private Timer businessFunctionTimer;
	private Observation businessFunctionObservation;

	@Autowired
	public RouteServiceWithObservationAPI(MeterRegistry meterRegistry, ObservationRegistry observationRegistry) {
		// this.meterRegistry = meterRegistry;
		this.observationRegistry = observationRegistry;

		// The new implementation based on the observation registry
		this.businessFunctionObservation = Observation.createNotStarted("businessFunctionTimerObservation",
				this.observationRegistry);
	}

	// The new implementation based on the observation registry
	public void businessFunctionObservation() {

		businessFunctionObservation.start();

		Integer randomValue = random.nextInt(1000);

		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		businessFunctionObservation.stop();

	}

	public boolean getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(boolean status) {
		this.serviceStatus = status;
	}
}
