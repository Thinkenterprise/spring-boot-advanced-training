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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.mongodb.RouteRepository;

@RestController
@RequestMapping("/routes")
public class RouteController {
	
	@Autowired
	private RouteRepository mongoRouteRepository;
	
	@RequestMapping
	public List<Route> getAll() {
		return mongoRouteRepository.findAll();
	}
	
	@RequestMapping("{id}")
	public Route get(@PathVariable String id) {
		return mongoRouteRepository.find(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Route post(@RequestBody Route route) {
		return mongoRouteRepository.save(route);
	}
	
}
