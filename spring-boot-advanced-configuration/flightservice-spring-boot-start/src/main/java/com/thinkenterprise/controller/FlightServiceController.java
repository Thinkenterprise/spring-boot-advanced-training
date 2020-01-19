/*
 * Copyright (C) 2016 Thinkenterprise
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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Flight;
import com.thinkenterprise.flightservice.model.FlightPrice;
import com.thinkenterprise.flightservice.service.FlightService;
import com.thinkenterprise.repository.FlightRepository;

@RestController
public class FlightServiceController {

	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	@RequestMapping("/flightService")
    public String flightService() {
		List<Flight> flights = flightRepository.findAll();
       	List<FlightPrice> flightPrices  = flights.stream().map(flight -> (FlightPrice)flight).collect(Collectors.toList());
       	return String.valueOf(flightService.totalPrice(flightPrices));
    }
}
