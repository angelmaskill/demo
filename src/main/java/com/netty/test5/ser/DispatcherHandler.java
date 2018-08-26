package com.netty.test5.ser;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import com.netty.Util.Person;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.sun.mail.iap.Response;

public class DispatcherHandler extends ChannelHandlerAdapter {

	public static final Logger LOGGER = LoggerFactory.getLogger(DispatcherHandler.class);

	// 业务逻辑线程池(业务逻辑最好跟netty io线程分开处理，线程切换虽会带来一定的性能损耗，但可以防止业务逻辑阻塞io线程)
	private final static ExecutorService workerThreadService = newBlockingExecutorsUseCallerRun(Runtime.getRuntime()
			.availableProcessors() * 2);

	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
		// ent protocol
		if (msg instanceof Person) {

			// 使用自定义业务线程处理复杂的业务逻辑，不会影响netty io线程
			workerThreadService.execute(new Runnable() {
				@Override
				public void run() {
					// 业务逻辑处理，可能会引起阻塞，
					Person person = act((Person) msg);
					LOGGER.info("BusinessHandler read msg from client :" + person);

					// netty writeAndFlush 已经做了处理，会主动切换到原生的io线程中完成剩下逻辑
					ctx.writeAndFlush(person);
				}

				// 此处是比较耗时的业务逻辑.
				private Person act(Person msg) {
					return msg;
				}
			});
		}
	}

	/**
	 * 阻塞的ExecutorService
	 *
	 * @param size
	 * @return
	 */
	public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
		return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						try {
							executor.getQueue().put(r);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				});
	}
}