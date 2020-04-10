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

package com.thinkenterprise.test;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class ReactorSamples {

	protected static Logger log = LoggerFactory.getLogger(ReactorSamples.class);

	@Test
	public void createFluxWithJust() {
		
	}

	@Test
	public void createFluxWithEmpty() {

	}

	@Test
	public void createFluxWithfromArray() {

	}

	@Test
	public void createFluxWithfromRange() {

	}

	@Test
	public void createMonoWithJust() {


	}

	@Test
	public void createMonoWithJustOrEmpty() {

	
	}

	@Test
	public void createFluxWithConsumerAsSubscription() {


	}

	@Test
	public void createFluxWithCustomerSubscription() {

		
	}

	public static class CustomerSubscription<T> extends BaseSubscriber<T> {

		public void hookOnSubscribe(Subscription subscription) {
			System.out.println("Subscribed");
			request(1);
		}

		public void hookOnNext(T value) {
			System.out.println(value);
			request(1);
		}
	}

	@Test
	public void createFluxWithFrameworkBlockSubcription() {

		
	}

	@Test
	public void createFluxWithMapOperator() {

		
	}

	@Test
	public void createFluxWithFilterAndSchedulerOperator() {

		
	}

	@Test
	public void createFluxProgrammaticWithGenerate() {

	}
	
	@Test
	public void testMonoWithStepVerifier() {	
			
	}

}
