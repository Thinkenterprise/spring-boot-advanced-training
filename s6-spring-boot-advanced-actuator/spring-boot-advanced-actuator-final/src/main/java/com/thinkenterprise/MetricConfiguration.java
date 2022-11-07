package com.thinkenterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetricConfiguration {

	@Bean
	TimedAspect timedAspect(MeterRegistry reg) {
		return new TimedAspect(reg);
	}

}
