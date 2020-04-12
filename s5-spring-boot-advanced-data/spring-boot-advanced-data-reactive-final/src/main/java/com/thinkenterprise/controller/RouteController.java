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

package com.thinkenterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.ReactiveRouteRepository;
import com.thinkenterprise.domain.route.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/routes")
public class RouteController {
	
	@Autowired
	private ReactiveRouteRepository reactiveRouteRepository;
	
	@RequestMapping
	public Flux<Route> getAll() {
		
		return reactiveRouteRepository.findAll();
	}
	
	@RequestMapping("{id}")
	public Mono<Route> get(@PathVariable Long id) {
		return reactiveRouteRepository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Mono<Route> post(@RequestBody Route route) {
		return reactiveRouteRepository.save(route);
	}
	
}
