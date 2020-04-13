package com.thinkenterprise.springboot.jpa2pc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String departure;
	private String destination;
	private String flightNumber;
	@OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Flight> flights = new ArrayList<Flight>();

	public Route() {}

	public Route( String flightNumber ) {
		this.flightNumber = flightNumber;
	}

	public Route( String flightNumber, String departure, String destination ) {
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.departure = departure;
	}


	public void addFlight( Flight flight ) {
		this.flights.add( flight );
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public Long getId() {
		return id;
	}

	public void setDeparture( String departure ) {
		this.departure = departure;
	}

	public void setDestination( String destination ) {
		this.destination = destination;
	}

	public void setFlightNumber( String number ) {
		this.flightNumber = number;
	}

	public void setFlights( List<Flight> flights ) {
		this.flights = flights;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", departure=" + departure + ", destination=" + destination + ", flightNumber=" + flightNumber + ", flights=" + flights + "]";
	}

}
