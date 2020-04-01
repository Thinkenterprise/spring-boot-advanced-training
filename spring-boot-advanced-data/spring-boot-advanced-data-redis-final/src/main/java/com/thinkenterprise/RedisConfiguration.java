package com.thinkenterprise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.thinkenterprise.domain.route.Route;

@Configuration
public class RedisConfiguration {
	
	@Bean
	public RedisTemplate<String, Route> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		
		RedisTemplate<String, Route> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
		return redisTemplate;
		
	}
	
	
	@Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Route> jackson2JsonRedisSerializer() {
    	Jackson2JsonRedisSerializer<Route> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Route.class);
        return jackson2JsonRedisSerializer;
    }
   
}
