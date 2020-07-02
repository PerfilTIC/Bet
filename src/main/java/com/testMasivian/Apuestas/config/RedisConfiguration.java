package com.testMasivian.Apuestas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.testMasivian.Apuestas.model.Bet;
import com.testMasivian.Apuestas.model.Roulette;

@Configuration
public class RedisConfiguration {
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	@Bean
	RedisTemplate<Integer, Roulette> redisRouletteTemplate() {
		final RedisTemplate<Integer, Roulette> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	@Bean
	RedisTemplate<Integer, Bet> redisBetTemplate() {
		final RedisTemplate<Integer, Bet> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
