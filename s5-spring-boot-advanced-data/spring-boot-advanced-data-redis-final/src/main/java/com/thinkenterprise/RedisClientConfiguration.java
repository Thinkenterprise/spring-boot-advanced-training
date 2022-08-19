package com.thinkenterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.thinkenterprise.domain.route.Route;

@Configuration
public class RedisClientConfiguration {

	@Bean
	public RedisTemplate<String, Route> redisTemplate(final RedisConnectionFactory redisConnectionFactory, final StringRedisSerializer keySerializer, final Jackson2JsonRedisSerializer<Route> valueSerializer){
		final RedisTemplate<String, Route> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(keySerializer);
		redisTemplate.setValueSerializer(valueSerializer);
		return redisTemplate;
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(final RedisProperties properties) {
		return new LettuceConnectionFactory(properties.getHost(), properties.getPort());
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean
	public Jackson2JsonRedisSerializer<Route> jackson2JsonRedisSerializer() {
		return new Jackson2JsonRedisSerializer<>(Route.class);
	}

}
