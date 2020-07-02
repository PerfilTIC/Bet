package com.testMasivian.Apuestas.repo;
import java.util.Map;
public interface RedisRepository {
	Map<Integer,Object> findAll(String key);
	Object findById(int id, String key);
	void save(Object object, String key);
	void delete(int id, String key);
}
