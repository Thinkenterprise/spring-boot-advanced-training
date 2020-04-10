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

public class ReactiveTests {

	protected static Logger log = LoggerFactory.getLogger(ReactiveTests.class);

	@Test
	public void createFluxWithJust() {
		@SuppressWarnings("unused")
		Flux<String> flux = Flux.just("Hello", "world");
	}

	@Test
	public void createFluxWithEmpty() {

		@SuppressWarnings("unused")
		Flux<String> flux = Flux.empty();
	}

	@Test
	public void createFluxWithfromArray() {

		@SuppressWarnings("unused")
		Flux<Integer> flux = Flux.fromArray(new Integer[] { 1, 2, 3 });
	}

	@Test
	public void createFluxWithfromRange() {

		@SuppressWarnings("unused")
		Flux<Integer> flux = Flux.range(1, 500);
	}

	@Test
	public void createMonoWithJust() {

		@SuppressWarnings("unused")
		Mono<String> mono = Mono.just("One");
	}

	@Test
	public void createMonoWithJustOrEmpty() {

		@SuppressWarnings("unused")
		Mono<String> stream6 = Mono.justOrEmpty(Optional.empty());
	}

	@Test
	public void createFluxWithConsumerAsSubscription() {

		Flux.range(1, 500).subscribe(i -> System.out.println(i));
	}

	@Test
	public void createFluxWithCustomerSubscription() {

		Flux.range(1, 500).subscribe(new CustomerSubscription<Integer>());
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

		Flux.range(1, 500).doOnNext(e -> log.info("{}" + e)).blockLast();
	}

	@Test
	public void createFluxWithMapOperator() {

		Flux.range(1, 500).map(e -> String.valueOf(e)).subscribe(i -> System.out.println(i));
	}

	@Test
	public void createFluxWithFilterAndSchedulerOperator() {

		Flux.range(1, 500).publishOn(Schedulers.elastic()).filter(i -> i < 20).subscribe(i -> System.out.println(i));
	}

	@Test
	public void createFluxProgrammaticWithGenerate() {

		Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
			sink.next("3 x " + state + " = " + 3 * state);
			if (state == 10)
				sink.complete();
			return state + 1;
		});
		
		flux.subscribe(e -> System.out.println(e));

	}
	
	@Test
	public void testMonoWithStepVerifier() {	
			Mono<String> mono = Mono.just("One");		
			StepVerifier.create(mono).expectNext("One").verifyComplete();
	}

}
