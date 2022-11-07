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
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class RouteService {

	private boolean serviceStatus;

	private Random random = new Random(0);

	private Stack<Long> values = new Stack<>();

	private MeterRegistry meterRegistry;
	private Counter businessFunctionCounter;
	private Gauge businessValueGauge;

	@Autowired
	public RouteService(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;

		businessFunctionCounter = Counter.builder("businessFunctionCounter")
				.description("number of business function calls").tags("business", "service")
				.register(this.meterRegistry);
		
		
		meterRegistry.gauge("businessValueGauge", values,v -> values.size());
		

		/*
		businessValueGauge = Gauge.builder("businessValueGauge", values, v -> values.size())
				.description("businessFunctionGauge").tags("business", "service").register(this.meterRegistry);
				*/
	}

	@Timed(value = "businessFunctionTimer", extraTags = { "business",
			"service" }, description = "Execution time of business function")
	public Long businessFunction() {

		Long randomValue = random.nextLong(1000L);

		try {
			Thread.sleep(random.nextLong(1000L));
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

	public boolean getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(boolean status) {
		this.serviceStatus = status;
	}
}
