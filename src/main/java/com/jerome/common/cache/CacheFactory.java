package com.jerome.common.cache;


/**
 * cache工厂
 */
public class CacheFactory {

    public static CacheI getRedisCache() {
        return RedisCachedImpl.getInstance();
    }

    /*
    public static CacheI getMemcachedCache() {
        return MemcachedCachedImpl.getInstance();
    }*/
}
