package com.thinkenterprise.springboot.jpa2pc.analytics;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.thinkenterprise.springboot.jpa2pc.domain.FlightStatus;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"time"
}))
public class Tracking {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private LocalDateTime time;
	private Long flightId;
	private FlightStatus status;


	public Tracking() {}

	public Tracking( LocalDateTime time, Long flightId, FlightStatus status ) {
		super();
		this.time = time;
		this.flightId = flightId;
		this.status = status;
	}

	public Tracking( Tracking tracking ) {
		id = tracking.getId();
		time = tracking.getTime();
		flightId = tracking.getFlightId();
		status = tracking.getStatus();
	}

	

	public LocalDateTime getTime() {
		return time;
	}

	public Long getFlightId() {
		return flightId;
	}

	public Long getId() {
		return id;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setTime( LocalDateTime time ) {
		this.time = time;
	}

	public void setFlightId( Long flightId ) {
		this.flightId = flightId;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setStatus( FlightStatus status ) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tracking [time=" + time + ", flightId=" + flightId + ", id=" + id + ", status=" + status + "]";
	}
}
