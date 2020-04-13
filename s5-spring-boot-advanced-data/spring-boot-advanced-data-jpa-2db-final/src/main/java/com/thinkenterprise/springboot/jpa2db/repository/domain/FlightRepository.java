package com.thinkenterprise.springboot.jpa2db.repository.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkenterprise.springboot.jpa2db.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {}