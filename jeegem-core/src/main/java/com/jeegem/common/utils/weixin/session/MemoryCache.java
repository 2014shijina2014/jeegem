package com.jeegem.common.utils.weixin.session;

/**
 * 本地缓存接口设计
 * 
 */
public interface MemoryCache<T> {

    T get(String key);

    void set(String key, T object);

    void remove(String key);

}
