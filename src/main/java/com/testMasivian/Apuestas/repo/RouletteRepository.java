package com.testMasivian.Apuestas.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.testMasivian.Apuestas.model.Roulette;
@Repository
public class RouletteRepository implements RedisRepository  {
	private RedisTemplate< Integer, Roulette> redisTemplate;
	private HashOperations hashOperation;
	public RouletteRepository(RedisTemplate< Integer, Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	@PostConstruct
	private void init() {
		hashOperation = redisTemplate.opsForHash();
	}
	@Override
	public Map<Integer, Object> findAll(String key) {
		return hashOperation.entries(key);
	}
	@Override
	public Object findById(int id, String key) {
		return (Roulette) hashOperation.get(key, id);
	}
	@Override
	public void save(Object object, String key) {
		Roulette roulette = (Roulette) object;
		hashOperation.put(key,roulette.getIdRoulette(), (Roulette) object);
	}
	@Override
	public void delete(int id, String key) {
		hashOperation.delete(key, id);
	}

} 
