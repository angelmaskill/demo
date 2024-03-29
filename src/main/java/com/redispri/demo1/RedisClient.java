package com.redispri.demo1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.ArrayList;
import java.util.List;

public class RedisClient {

    private Jedis jedis;// 非切片额客户端连接
    private JedisPool jedisPool;// 非切片连接池
    private ShardedJedis shardedJedis;// 切片额客户端连接
    private ShardedJedisPool shardedJedisPool;// 切片连接池

    public RedisClient() {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();

    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setMaxActive(20);
        config.setMaxIdle(5);
        //config.setMaxWait(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setMaxActive(20);
        config.setMaxIdle(5);
        //config.setMaxWait(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public void show() {
        KeyOperate();
        StringOperate();
        ListOperate();
        SetOperate();
        SortedSetOperate();
        HashOperate();
        jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    }

    /**
     * Handle jedisException, write log and return whether the connection is broken.
     */
    protected boolean handleJedisException(JedisException jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            System.out.println("Redis connection " + jedisPool + " lost." + jedisException.getLocalizedMessage());
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
                System.out.println("Redis connection " + jedisPool + " are read-only slave." + jedisException.getLocalizedMessage());
            } else {
                // dataException, isBroken=false
                return false;
            }
        } else {
            System.out.println("Jedis exception happen." + jedisException.getLocalizedMessage());
        }
        return true;
    }

    /**
     * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
     */
    protected void closeResource(Jedis jedis, boolean conectionBroken) {
        try {
            if (conectionBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            System.out.println("return back jedis failed, will fore close the jedis.");
            jedisPool.close();
        }
    }

    private void KeyOperate() {

    }

    private void StringOperate() {

    }

    private void ListOperate() {

    }

    private void SetOperate() {

    }

    private void SortedSetOperate() {

    }

    private void HashOperate() {

    }
}