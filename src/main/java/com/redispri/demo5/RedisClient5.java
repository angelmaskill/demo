package com.redispri.demo5;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

public class RedisClient5 {
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedisPool shardedJedisPool;//切片连接池
    private static RedisClient5 redisClient;

    private RedisClient5() {
        initialPool();
        initialShardedPool();
    }

    public static RedisClient5 getRedisClient() {
        if (redisClient == null) {
            synchronized (RedisClient5.class) {
                if (redisClient == null) redisClient = new RedisClient5();
            }
        }
        return redisClient;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public void Close() {
        jedisPool.close();
        shardedJedisPool.close();
    }
}