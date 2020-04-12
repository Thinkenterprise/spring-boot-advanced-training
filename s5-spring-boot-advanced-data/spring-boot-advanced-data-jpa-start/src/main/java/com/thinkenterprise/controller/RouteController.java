package com.thinkenterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.jpa.RouteRepository;

@RestController
@RequestMapping("/routes")
public class RouteController {
	
	@Autowired
	private RouteRepository routeRepository;
	
	@RequestMapping
	public List<Route> getAll() {
		
		return routeRepository.findAll();
	}
	
	@RequestMapping("{id}")
	public Route get(@PathVariable Long id) {
		return routeRepository.find(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Route post(@RequestBody Route route) {
		return routeRepository.save(route);
	}
	
}
