package com.thinkenterprise;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;

@Component
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
