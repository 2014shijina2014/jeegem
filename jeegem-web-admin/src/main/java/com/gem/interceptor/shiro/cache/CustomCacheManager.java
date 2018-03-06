package com.gem.interceptor.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 
 * <p>
 * Title: CustomCacheManager.java
 * </p>
 * 
 * <p>
 * Description: 自定义缓存管理器
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * <p>
 * Company: www.jeegem.com
 * </p>
 * 
 * @author JeeGem
 * 
 * @date 2018年3月7日 上午5:54:45
 * 
 * @version JeeGem V3.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CustomCacheManager implements CacheManager {

	private RedisCache redisCache;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return this.redisCache;
	}

	public void setRedisCache(RedisCache redisCache) throws CacheException {
		this.redisCache = redisCache;
	}

}