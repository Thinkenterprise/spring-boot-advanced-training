package com.thinkenterprise.springboot.jpa2db.repository.analytics;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkenterprise.springboot.jpa2db.analytics.Tracking;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {}
