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

package com.thinkenterprise.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RouteEventEmitter {

		public Mono<ServerResponse> startEmitter(ServerRequest serverRequest) {
			return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(body(), Route.class);
		}
		
		Flux<Route> body() {
			
			 Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
	         Flux<Route> events = Flux.fromStream(Stream.generate(()->new Route("LH" +LocalDateTime.now(),"FRA","NYC")));
	         return Flux.zip(events, interval, (key, value) -> key);
		}
		
}
