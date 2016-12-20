package com.redispri.demo4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

/**
 * @author WHD *2015-4-19
 */
public class RedisClient4 {
	// 获取数据库的连接,非切片客户端连接
	private Jedis jedis;
	// 非切片连接池
	private JedisPool jedisPool = null;
	// 切片客户端
	private ShardedJedis shardedJedis;
	// 切片连接池
	private ShardedJedisPool shardedJedisPool;

	// 构造函数
	public RedisClient4() {
		// 初始化连接池
		initialPool();
		// 初始化切片连接池
		initialShardedPool();
		// 从切片池中获取切片实例
		shardedJedis = shardedJedisPool.getResource();
		// 从非切片池中获取实例
		jedis = jedisPool.getResource();
	}

	// 初始化非切片池
	private void initialPool() {
		// 池的配置
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接数
		// config.setMaxActive(20);
		// 最大空闲连接数
		config.setMaxIdle(5);
		// 获取连接时的最大等待毫秒数
		// config.setMaxWait(1000);
		// 在空闲时检查有效性, 默认false
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	// 初始化切片池
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接个数
		// config.setMaxActive(20);
		// 最大空闲连接数
		config.setMaxIdle(2);
		// 获取连接时的最大等待毫秒数
		// config.setMaxWait(10001);
		// 在空闲时检查有效性,默认false
		config.setTestOnBorrow(false);
		// slave 连接, 这里实现了集群的功能，配置多个redis服务实现请求的分配进行负载均衡
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.0.106", 6379, "master"));
		shards.add(new JedisShardInfo("192.168.0.100", 6379, "master"));
		// shardedJedisPool= newShardedJedisPool(config,shards);
	}

	public void show() {
		KeyOperate();
		StringOperate();
		ListOperate();
		// 释放连接
		jedisPool.returnResource(jedis);
		// 释放连接
		shardedJedisPool.returnResource(shardedJedis);
	}

	//
	private void KeyOperate() {
		System.out.println("清空所有数据库" + jedis.flushDB());
		// 判断key是否存在
		System.out.println("判断key999建是否存在" + shardedJedis.exists("key999"));
		System.out.println("新曾key001value001键值对" + shardedJedis.set("key001", "value001"));
		System.out.println("判断key001是否存在" + shardedJedis.exists("key001"));
		// 输出系统中所有的key
		System.out.println("新增key002value002" + shardedJedis.set("key002", "value002"));
		System.out.println("系统中的所有键如下：");
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key);
		}
		// 删除某个key 如果不从在则忽略
		System.out.println("系统中删除key002" + jedis.del("key002"));
		System.out.println("判断是否存在" + jedis.exists("key002"));
		// 设置key 001 的过期时间
		System.out.println("设置key001的过期时间为五秒" + jedis.expire("key001", 5));
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查看某个key的剩余时间 单位秒 不存在或永久 返回-1
		System.out.println("查看key001的剩余时间" + jedis.ttl("key001"));
		// 移除某个key的剩余时间
		System.out.println("删除key001的剩余时间" + jedis.persist("key001"));
		// 移除后在看时间
		System.out.println("剩余时间" + jedis.ttl("key001"));
		// 查看key 所存储的值的类型
		System.out.println("查看key所存储的值的类型" + jedis.type("key001"));
	}

	/**
	 * 
	 * String 类型
	 */
	private void StringOperate() {
		System.out.println("+=+++++++String+++++++===");
		// 清空数据
		System.out.println("清空数据库中的数据" + jedis.flushDB());
		System.out.println("========曾==========");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("以新增3个键值对 如下：");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));
		System.out.println("=======删除======");
		System.out.println("删除key003键值对" + jedis.del("key003"));
		System.out.println("获取key003建对应的值" + jedis.get("key003"));
		System.out.println("======改=====");
		// 直接覆盖原来的值
		System.out.println("直接覆盖原来的值" + jedis.set("key001", "value001-update"));
		System.out.println("获取key001 对应的新值 " + jedis.get("key001"));
		// 如果有的话 则不修改如果没有的话则要修改
		System.out.println("没有添加，有则无效" + jedis.setnx("key001", "value001 a new "));
		// 获取这个值看看那
		System.out.println("修改了之后应该是value001  a  new  获取这个值" + jedis.get("key001"));
		// 直接覆盖原来的数据
		System.out.println("在ke0y002原值后面追加" + jedis.append("key002", "appendString"));
		System.out.println("获取key002的新值" + jedis.get("key002"));
		System.out.println("======曾，删查多个=======");
		/**
		 * 
		 * mset mget 同时新增 修改 查询多个键值对
		 */
		// 一次性新增多个值
		System.out.println(jedis.mset("key201", "value201", "key202", "value202", "key203", "value203", "key204",
				"value204"));
		// 一次获得多个建的值
		System.out.println(jedis.mget("key201", "key202", "key203", "key204"));
		// 一次性删除多个值
		System.out.println("一次性删除多个值" + jedis.del(new String[] { "key201", "key202", "key203", "key204" }));
		// jedis 中有的方法在 shardedJedis 中也可以直接使用，下面测试一些前面没有使用过的方法
		System.out.println("=========STRING2====");
		System.out.println("清空所有数据库" + jedis.flushDB());
		System.out.println("原先   key301不存在的时候 新增301" + shardedJedis.setnx("key301", "value301"));
		System.out.println("原先key302 不存在的时候新增 302" + shardedJedis.setnx("key302", "value302"));
		System.out.println("原先key302存在时试着新增 302" + shardedJedis.setnx("key302", "value302"));
		// 获取key3... 的值
		System.out.println("获取key301的值" + shardedJedis.get("key301"));
		System.out.println("获取key302的值" + shardedJedis.get("key302"));
		// 超期 有效键值对被删除
		System.out.println("=======");
		System.out.println("key303的有效时间为2" + shardedJedis.setex("key303", 2, "key303-2second"));
		System.out.println("获取key303 的值" + shardedJedis.get("key303"));
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		System.out.println("过期后的key303的值" + shardedJedis.get("key303"));
		// 获取原值，更新为新值一步完成
		System.out.println("key302 的原值" + shardedJedis.getSet("key302", "value302-aftergetset"));
		System.out.println("key302 的新值" + shardedJedis.get("key302"));
		// 获取某个key 的子串
		System.out.println("获取key302对应值中的子串" + shardedJedis.getrange("key302", 2, 4));
	}

	/**
	 * 
	 * list 类型
	 */
	private void ListOperate() {
		System.out.println("=======list======11");
		// 清空数据
		System.out.println("清空数据库中的所有数据" + jedis.flushDB());
		// 添加数据
		System.out.println("====增====");
		shardedJedis.lpush("stringlists", "vectory");
		shardedJedis.lpush("stringlists", "arraulist");
		shardedJedis.lpush("stringlists", "vectory");
		shardedJedis.lpush("stringlists", "vectory");
		shardedJedis.lpush("stringlists", "linkedlist");
		shardedJedis.lpush("stringlists", "maplist");
		shardedJedis.lpush("stringlists", "hashlist");
		shardedJedis.lpush("numberlist", "1");
		shardedJedis.lpush("numberlist", "2");
		shardedJedis.lpush("numberlist", "3");
		shardedJedis.lpush("numberlist", "4");
		shardedJedis.lpush("numberlist", "5");
		shardedJedis.lpush("numberlist", "6");
		// 获取stringlist 的所有数据
		System.out.println("所有数据stringlists" + shardedJedis.lrange("stringlists", 0, -1));
		List<String> stringlist = shardedJedis.lrange("stringlists", 0, -1);
		System.out.println("stringlist 的长度" + stringlist.size());
		// 获取 numberlist 的所有数据
		System.out.println("获取numberlist的所有数据" + shardedJedis.lrange("numberlist", 0, -1));
		List<String> numberlist = shardedJedis.lrange("numberlist", 0, -1);
		System.out.println("numberlist 的长度" + numberlist.size());
		// 元素的删除
		System.out.println("元素的删除");
		// 删除列表指定的值，第二个参数为删除个数（如果有重复）后添加的先删除，类似栈
		System.out.println("删除指定的元素" + shardedJedis.lrem("stringlists", 2, "vectory"));
		System.out.println("删除指定的元素在获取所有元素" + shardedJedis.lrange("stringlists", 0, -1));
		// 删除制定区间以外的数据
		System.out.println("删除指定区间以外的数据" + shardedJedis.ltrim("stringlists", 0, 3));
		// 获取删除指定元素外的的数据
		System.out.println("删除指定区间的数据" + shardedJedis.lrange("stringlists", 0, -1));
		// 列表元素出栈
		System.out.println("列表元素出栈" + shardedJedis.lpop("stringlists"));
		// 出栈后数据的获取
		System.out.println("出栈后获取所有元素" + shardedJedis.lrange("stringlists", 0, -1));
		// 修改列表中制定下标的值：
		System.out.println("修改列表中指定下标的值" + shardedJedis.lset("stringlists", 0, "hello world"));
		// 获取修改制定下标的值
		System.out.println("修改后的值" + shardedJedis.lrange("stringlists", 0, -1));
		// 查询 某个key的 数组的长度
		System.out.println("获取长度shardedJedis的" + shardedJedis.llen("stringlists"));
		// 查询 某个key的 数组的长度
		System.out.println("获取长度numberlist的" + shardedJedis.llen("numberlist"));
		// 排序 list中存字符串时必须指定参数为alpha，如果不使用sortingparams，而是直接使用sort("list")
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.alpha();
		sortingParameters.limit(0, 3);
		// 排序后的结果
		System.out.println("返回排序后的结果stringlists" + shardedJedis.sort("stringlists", sortingParameters));
		System.out.println("返回排序后的结果 numberlist" + shardedJedis.sort("numberlist"));
		// 获取子串 -1 代表倒数第一个 -2 代表倒数第二个
		System.out.println("子串第二个开始到结束" + shardedJedis.lrange("stringlists", 1, -1));
		// 获取 第二个到倒数第二个
		System.out.println("获取子串" + shardedJedis.lrange("stringlists", 1, -2));
		// 获取制定下标的数据
		System.out.println("获取stringlist 的指定的下标的值" + shardedJedis.lindex("stringlists", 2));
	}
}