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
package com.thinkenterprise.actuator.endpoint.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.thinkenterprise.service.RouteService;

import reactor.core.publisher.Mono;

@Component
@Endpoint(id="reactiveRouteService")
public class ReactiveRouteServiceEndpoint {

	@Autowired
	private RouteService routeService;
	
	
	@ReadOperation
	public Mono<Boolean> getServiceStatus() {
		return Mono.just(routeService.getServiceStatus());
	}
	
}
