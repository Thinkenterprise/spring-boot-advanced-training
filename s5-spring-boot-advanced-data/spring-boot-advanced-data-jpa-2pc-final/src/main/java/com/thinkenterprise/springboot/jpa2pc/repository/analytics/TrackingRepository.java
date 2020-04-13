package com.thinkenterprise.springboot.jpa2pc.repository.analytics;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkenterprise.springboot.jpa2pc.analytics.Tracking;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {}
