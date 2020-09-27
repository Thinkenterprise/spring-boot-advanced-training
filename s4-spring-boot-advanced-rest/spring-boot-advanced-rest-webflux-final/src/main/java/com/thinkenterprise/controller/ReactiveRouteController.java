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


import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;


@RestController
@RequestMapping("routes")
@Profile("controller")
public class ReactiveRouteController {

		
	@RequestMapping
	public Flux<Route> routes() {
		
		return Flux.just(new Route("LH7902","MUC","IAH"), 
					new Route("LH1602","MUC","IBZ"), 
					new Route("LH401","FRA","NYC"));
	
	}
	 
}
