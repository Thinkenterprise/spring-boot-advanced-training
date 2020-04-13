package com.thinkenterprise.springboot.jpa2pc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private double price;
	
	@ManyToOne
	private Route route;

	public Flight() {}

	public Flight( double price, LocalDate date ) {
		this.price = price;
		this.date = date;
	}


	public LocalDate getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public Route getRoute() {
		return route;
	}

	public void setDate( LocalDate date ) {
		this.date = date;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setPrice( double price ) {
		this.price = price;
	}

	public void setRoute( Route route ) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", date=" + date + ", price=" + price + ", route=" + route + "]";
	}

}
