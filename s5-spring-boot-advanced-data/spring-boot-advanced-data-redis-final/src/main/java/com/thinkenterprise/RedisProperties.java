package com.thinkenterprise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisProperties {

	private final int port;
	private final String host;

	public RedisProperties(@Value("${spring.redis.server.port}") final int port,
			@Value("${spring.redis.server.host}") final String host) {
		this.port = port;
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}
