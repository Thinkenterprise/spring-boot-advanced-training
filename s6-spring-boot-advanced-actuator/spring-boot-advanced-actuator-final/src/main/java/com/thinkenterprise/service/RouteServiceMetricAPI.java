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

import java.time.Duration;
import java.util.Random;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class RouteServiceMetricAPI {

	private boolean serviceStatus;

	private Random random = new Random();

	private Stack<Integer> values = new Stack<>();

	private MeterRegistry meterRegistry;
	private Counter businessFunctionCounter;
	private Timer businessFunctionTimer;

	@Autowired
	public RouteServiceMetricAPI(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;

		businessFunctionCounter = Counter.builder("businessFunctionCounter")
				.description("number of business function calls").tags("business", "service")
				.register(this.meterRegistry);
		
		businessFunctionTimer = Timer.builder("businessFunctionTimeMeter")
				.description("number of business function calls").tags("business", "service")
				.register(this.meterRegistry);

		meterRegistry.gauge("businessValueGauge", values, v -> values.size());

	}

	@Timed(value = "businessFunctionTimer", extraTags = { "business",
			"service" }, description = "Execution time of business function")
	public Integer businessFunction() {

		Integer randomValue = random.nextInt(1000);

		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (randomValue > 500L)
			values.push(randomValue);
		else if (!values.empty())
			values.pop();

		businessFunctionCounter.increment();
		return randomValue;
	}


	public void businessFunctionMeter() {

		Long timeStart = System.nanoTime();

		Integer randomValue = random.nextInt(1000);

		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Long timeStop = System.nanoTime();

		businessFunctionTimer.record(Duration.ofNanos(timeStop - timeStart));
	}

	public boolean getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(boolean status) {
		this.serviceStatus = status;
	}
}
