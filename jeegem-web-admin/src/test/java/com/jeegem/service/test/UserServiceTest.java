package com.jeegem.service.test;

import org.apache.shiro.cache.CacheManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeegem.common.utils.SerializeUtil;
import com.jeegem.interceptor.shiro.cache.RedisCache;

import net.sf.ehcache.config.Searchable;
import redis.clients.jedis.JedisPool;

public class UserServiceTest extends BaseJunitTest {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private CacheManager redisCacheManager;
	
	
	
	@Test
	public void testJedisPool(){
		redisCache.put("tttmmmmm", "范德萨甲方收到");
		
		Object v = redisCache.get("tttmmmmm");
		
		System.out.println(v);
		
		RedisCache redisCache2 = (RedisCache) redisCacheManager.getCache("tttmmmmm");
		
		Object v2 = redisCache.get("tttmmmmm");
		
		System.out.println(v2);
		
		
	}
	
	
	
	
}
