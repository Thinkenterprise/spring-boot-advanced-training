/*
 * Copyright (C) 2020 Thinkenterprise
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

package com.thinkenterprise.controller;


import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Profile("controller")
public class ReactiveRouteController {
	
	
	private List<Route> airline = Arrays.asList(new Route("LH7902","MUC","IAH"), 
												new Route("LH1602","MUC","IBZ"), 
												new Route("LH401","FRA","NYC"));

	
	private Random random = new Random();
	
	@RequestMapping("routes")
	public Flux<Route> routes() {
		
		return Flux.just(new Route("LH7902","MUC","IAH"), 
					new Route("LH1602","MUC","IBZ"), 
					new Route("LH401","FRA","NYC"));
						
	}
	
	@RequestMapping("routes/{id}")
	public Mono<Route> routes(@PathVariable Long id) {
		return Mono.just(new Route("LH401","FRA","NYC"));
	}
	
	
	//@RequestMapping(value ="stream", produces = "application/stream+json")
	@RequestMapping("stream")
	public Flux<Route> routesAsStream() {		
		  return Flux.<Route> generate(sink ->
		  sink.next(airline.get(random.nextInt(airline.size()))))
		  .delayElements(Duration.ofMillis(1000)); 
	}
	
	@RequestMapping("throwException")
	public Flux<Route> routesAsException() {
		  throw new RuntimeException("Web Client Exception");
	}
	
		
	@ExceptionHandler(value = RuntimeException.class)
	Mono<ResponseEntity<ProblemDetail>> hndleException(RuntimeException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setType(URI.create("http://thinkenterprise.com/RuntimeException"));
		problemDetail.setTitle("Runtime Exception");
		problemDetail.setDetail(exception.getMessage());

		return Mono.just(new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.BAD_REQUEST));
	}
	
	 
}
