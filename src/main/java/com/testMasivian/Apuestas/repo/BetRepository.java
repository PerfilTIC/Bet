package com.testMasivian.Apuestas.repo;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.testMasivian.Apuestas.model.Bet;
import com.testMasivian.Apuestas.model.Roulette;
@Repository
public class BetRepository implements RedisRepository {
	private RedisTemplate< Integer, Bet> redisTemplate;
	private HashOperations hashOperation;
	public BetRepository(RedisTemplate< Integer, Bet> redisTemplate) {
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
		return (Bet) hashOperation.get(key, id);
	}
	@Override
	public void save(Object object, String key) {
		Bet bet = (Bet) object;
		hashOperation.put(key,bet.getIdBet(), (Bet) object);
	}
	@Override
	public void delete(int id, String key) {
		hashOperation.delete(key, id);
	}
}
