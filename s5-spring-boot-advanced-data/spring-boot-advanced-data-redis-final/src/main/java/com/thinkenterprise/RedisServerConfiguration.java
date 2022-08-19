package com.thinkenterprise;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;

@Configuration
public class RedisServerConfiguration {

	private final RedisServer server;

	public RedisServerConfiguration(final RedisProperties properties) {
		server = new RedisServer(properties.getPort());
	}

	@PostConstruct
	public void postConstruct() {
		server.start();
	}

	@PreDestroy
	public void preDestroy() {
		server.stop();
	}

}
