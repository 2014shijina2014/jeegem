package com.jeegem.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.common.utils.SerializeUtil;
import com.gem.interceptor.shiro.cache.RedisCache;

import net.sf.ehcache.config.Searchable;
import redis.clients.jedis.JedisPool;

public class UserServiceTest extends BaseJunitTest {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	private RedisCache redisCache;
	
	@Test
	public void testJedisPool(){
		redisCache.put("tttmmmmm", "范德萨甲方收到");
		
		Object v = redisCache.get("tttmmmmm");
		
		System.out.println(v);
		
		
	}
	
	
	
	
}
