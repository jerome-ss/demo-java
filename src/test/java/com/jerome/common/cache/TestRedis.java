package com.jerome.common.cache;

import org.junit.Test;

public class TestRedis {

    @Test
    public void putTest() {
        CacheI cache = CacheFactory.getRedisCache();
        boolean b = cache.put("name", "jerome");
        System.out.println("push result = " + b);
    }

    @Test
    public void getTest() {
        CacheI cache = CacheFactory.getRedisCache();
        Object name = cache.get("name");
        System.out.println(name.toString());
    }

}
