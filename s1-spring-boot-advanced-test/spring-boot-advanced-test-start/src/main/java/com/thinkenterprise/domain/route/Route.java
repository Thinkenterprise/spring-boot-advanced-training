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
 * @author Rafael Kansy
 * @author Michael Schaefer
 */

package com.thinkenterprise.domain.route;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thinkenterprise.domain.core.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@NamedEntityGraphs({
	@NamedEntityGraph(name = "routeFlight", attributeNodes = {@NamedAttributeNode("flights")}),
	@NamedEntityGraph(name = "routeFlightAircraft", attributeNodes = {@NamedAttributeNode(value = "flights")})
})
public class Route extends AbstractEntity {



	@NotNull
	private String flightNumber;
	@NotNull
	private String departure;
	@NotNull
	private String destination;

	private LocalTime departureTime;
	private LocalTime arrivalTime;

	@Enumerated(EnumType.ORDINAL)
	@ElementCollection(targetClass = DayOfWeek.class)
	private Set<DayOfWeek> scheduledWeekdays = new HashSet<>();

	private String aircraft;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn(name = "date")
	private List<Flight> flights = new ArrayList<>();

	@Transient
	private Double total;

	public Route() {
		super();
	}

	public Route(final String flightNumber) {
		super();
		this.flightNumber = flightNumber;
	}

	public Route(final String flightNumber, final String departure, final String destination) {
		super();
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.departure = departure;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(final String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(final String destination) {
		this.destination = destination;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(final String number) {
		flightNumber = number;
	}

	public void addFlight(final Flight flight) {
		flights.add(flight);
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(final List<Flight> flights) {
		this.flights = flights;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(final Double total) {
		this.total = total;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(final LocalTime time) {
		departureTime = time;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(final LocalTime time) {
		arrivalTime = time;
	}

	public Set<DayOfWeek> getScheduledWeekdays() {
		return scheduledWeekdays;
	}

	public void setScheduledWeekdays(final Set<DayOfWeek> scheduledWeekday) {
		scheduledWeekdays = scheduledWeekday;
	}

	public void addScheduledWeekday(final DayOfWeek scheduledWeekday) {
		scheduledWeekdays.add(scheduledWeekday);
	}

	public void addScheduledDaily() {
		scheduledWeekdays.add(DayOfWeek.MONDAY);
		scheduledWeekdays.add(DayOfWeek.TUESDAY);
		scheduledWeekdays.add(DayOfWeek.WEDNESDAY);
		scheduledWeekdays.add(DayOfWeek.THURSDAY);
		scheduledWeekdays.add(DayOfWeek.FRIDAY);
		scheduledWeekdays.add(DayOfWeek.SATURDAY);
		scheduledWeekdays.add(DayOfWeek.SUNDAY);
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(final String aircraft) {
		this.aircraft = aircraft;
	}
}
