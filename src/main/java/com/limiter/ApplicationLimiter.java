package com.limiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

/**
 * 应用级限流
 *
 * @author
 * @since 16/6/13
 */
public class ApplicationLimiter {

	private AtomicLong atomicLong = new AtomicLong(0);

	private static final int LIMIT = 1000;

	/**
	 * <pre>
	 *     限制某个接口的总并发数/请求数（伪代码）
	 * </pre>
	 */
	public void handleByLimiter() {
		try {
			if (atomicLong.incrementAndGet() > LIMIT) {
				// 拒绝请求
			}
			// 处理请求

		} finally {
			atomicLong.decrementAndGet();

		}
	}

	/**
	 * 时间窗口内限制并发数(限制某个接口/服务每秒/每分钟/每天的请求数/调用量)
	 */

	public void handleByLimiterAtFiXedTime() throws Exception {
		// Guava的Cache来存储计数器,过期时间设置为2秒（保证1秒内的计数器是有的）
		LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS)
				.build(new CacheLoader<Long, AtomicLong>() {
					@Override
					public AtomicLong load(Long key) throws Exception {
						return new AtomicLong(0);
					}
				});
		long limit = 1000;
		while (true) {
			// 得到当前的秒数,控制每秒的请求数量
			long currentSeconds = System.currentTimeMillis() / 1000;
			// 我们获取当前时间戳然后取秒数来作为KEY进行计数统计和限流
			if (counter.get(currentSeconds).incrementAndGet() > limit) {
				System.out.println("限流了：" + currentSeconds);
				continue;
			}
			// 处理业务逻辑
		}
	}

	/**
	 * <pre>
	 * 1. RateLimiter.create(5) 表示桶容量为5且每秒新增5个令牌，即每隔200毫秒新增一个令牌；
	 * 2. limiter.acquire()表示消费一个令牌，如果当前桶中有足够令牌则成功（返回值为0），如果桶中没有令牌则暂停一段时间，比如发令牌间隔是200毫秒，则等待200毫秒后再去消费令牌
	 * </pre>
	 */
	public void handleByBucketToken() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
		RateLimiter rateLimiter = RateLimiter.create(5);
		while (true) {
			rateLimiter.acquire();
			System.out.println(simpleDateFormat.format(new Date()));
		}
	}
}