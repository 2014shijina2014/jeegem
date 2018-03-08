package com.jeegem.interceptor.shiro.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.jeegem.common.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * <p>
 * Title: RedisCache.java
 * </p>
 * 
 * <p>
 * Description: 缓存管理器
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
 * @date 2018年3月7日 上午6:05:38
 * 
 * @version JeeGem V3.0
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class RedisCache<K, V> implements Cache<K, V> {
	private JedisPool jedisPool;
	
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	@Override
	public Object get(Object key) throws CacheException {

		byte[] bytes = SerializeUtil.serialize(key);
		byte[] value = jedisPool.getResource().get(bytes);
		if (value == null) {
			return null;
		}
		return SerializeUtil.deserialize(value);
	}

	/**
	 * 将shiro的缓存保存到redis中
	 */
	@Override
	public Object put(Object key, Object value) throws CacheException {

		Jedis jedis = jedisPool.getResource();

		jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
		byte[] bytes = jedis.get(SerializeUtil.serialize(key));
		Object object = SerializeUtil.deserialize(bytes);

		return object;

	}

	@Override
	public Object remove(Object key) throws CacheException {
		Jedis jedis = jedisPool.getResource();

		byte[] bytes = jedis.get(SerializeUtil.serialize(key));

		jedis.del(SerializeUtil.serialize(key));

		return SerializeUtil.deserialize(bytes);
	}

	/**
	 * 清空所有缓存
	 */
	@Override
	public void clear() throws CacheException {
		jedisPool.getResource().flushDB();
	}

	/**
	 * 缓存的个数
	 */
	@Override
	public int size() {
		Long size = jedisPool.getResource().dbSize();
		return size.intValue();
	}

	/**
	 * 获取所有的key
	 */
	@Override
	public Set keys() {
		Set<byte[]> keys = jedisPool.getResource().keys(new String("*").getBytes());
		Set<Object> set = new HashSet<Object>();
		for (byte[] bs : keys) {
			set.add(SerializeUtil.deserialize(bs));
		}
		return set;
	}

	/**
	 * 获取所有的value
	 */
	@Override
	public Collection values() {
		Set keys = this.keys();
		List<Object> values = new ArrayList<Object>();
		for (Object key : keys) {
			byte[] bytes = jedisPool.getResource().get(SerializeUtil.serialize(key));
			values.add(SerializeUtil.deserialize(bytes));
		}
		return values;
	}
}
