package com.thinkenterprise.springboot.jpa2pc.repository.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkenterprise.springboot.jpa2pc.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {}