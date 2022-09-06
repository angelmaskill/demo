package com.netty.test16;


import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-5 11:53
 * @description：
 * @modified By：
 * @version:
 */
public class ComparePriceService {

    private ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private final String product = "iphone";

    /**
     * 【串行】获取多个平台比价信息得到最低价格平台
     *
     * @return
     */
    @Test
    public void getCheapestPlatAndPrice() {

        // 获取某宝的价格以及优惠
        PriceResult mouBaoPrice = computeRealPrice(HttpRequestMock.getMouBaoPrice(product),
                                                   HttpRequestMock.getMouBaoDiscounts(product));
        // 获取某东的价格以及优惠
        PriceResult mouDongPrice = computeRealPrice(HttpRequestMock.getMouDongPrice(product),
                                                    HttpRequestMock.getMouDongDiscounts(product));
        // 获取某夕夕的价格以及优惠
        PriceResult mouXiXiPrice = computeRealPrice(HttpRequestMock.getMouXiXiPrice(product),
                                                    HttpRequestMock.getMouXiXiDiscounts(product));

        // 计算并选出实际价格最低的平台
        PriceResult priceResult = Stream.of(mouBaoPrice, mouDongPrice, mouXiXiPrice).
                min(Comparator.comparingInt(PriceResult::getRealPrice))
                .get();
        System.out.println(priceResult);
    }

    /**
     * 演示传统方式通过线程池来增加并发
     * 问题：不知道需要等多久才能拿到结果
     *
     * @return
     */
    @Test
    public void getCheapestPlatAndPrice2() {
        Future<PriceResult> mouBaoFuture =
                threadPool.submit(() -> computeRealPrice(HttpRequestMock.getMouBaoPrice(product),
                                                         HttpRequestMock.getMouBaoDiscounts(product)));
        Future<PriceResult> mouDongFuture =
                threadPool.submit(() -> computeRealPrice(HttpRequestMock.getMouDongPrice(product),
                                                         HttpRequestMock.getMouDongDiscounts(product)));
        Future<PriceResult> mouXiXiFuture =
                threadPool.submit(() -> computeRealPrice(HttpRequestMock.getMouXiXiPrice(product),
                                                         HttpRequestMock.getMouXiXiDiscounts(product)));

        // 等待所有线程结果都处理完成，然后从结果中计算出最低价
        PriceResult priceResult = Stream.of(mouBaoFuture, mouDongFuture, mouXiXiFuture)
                .map(priceResultFuture -> {
                    try {
                        //如有必要，最多等待给定时间以完成计算，然后检索其结果（如果可用）。
                        return priceResultFuture.get(5L, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(PriceResult::getRealPrice))
                .get();
        System.out.println(priceResult);
    }

    /**
     * 演示并行处理的模式
     * thenCombine: 将两个CompletableFuture对象组合起来进行下一步处理，可以拿到两个执行结果，并传给自己的执行函数进行下一步处理，最后返回一个新的CompletableFuture对象。
     *
     * @return
     */
    @Test
    public void getCheapestPlatAndPrice3() {
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouBao =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                     this::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouDong =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongDiscounts(product)),
                                     this::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouXiXi =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiDiscounts(product)),
                                     this::computeRealPrice);

        // 排序并获取最低价格
        // 在执行线程中将任务放到工作线程中进行处理的时候，执行线程与工作线程之间是异步执行的模式，如果执行线程需要获取到共工作线程的执行结果，
        // 则可以通过get或者join方法，阻塞等待并从CompletableFuture中获取对应的值。
        PriceResult priceResult = Stream.of(mouBao, mouDong, mouXiXi)
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();
        System.out.println(priceResult);
    }


    /**
     * 演示thenCombine与thenCombineAsync区别
     * thenCombineAsync两个入参的方法，使用默认的ForkJoinPool线程池中的工作线程进行处理
     * themCombineAsync三个入参的方法，支持自定义线程池并指定使用自定义线程池中的线程作为工作线程去处理待执行任务。
     *
     * @param product
     * @return
     */
    public PriceResult getCheapestPlatAndPrice4(String product) {
        // 构造自定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        return
                CompletableFuture.supplyAsync(
                        () -> HttpRequestMock.getMouXiXiPrice(product),
                        executor
                ).thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiDiscounts(product)),
                        this::computeRealPrice,
                        executor
                ).join();
    }

    /**
     * 演示thenCombine与thenCombineAsync区别
     * thenCombine: 沿用上一个执行任务所使用的线程池进行处理
     * <p>
     * 没有指定线程池，所以两个supplyAsync方法都是用的默认的ForkJoinPool线程池，而thenCombine使用的是上一个任务所使用的线程池，所以也是用的ForkJoinPool
     *
     * @return
     */
    @Test
    public void getCheapestPlatAndPrice5() {
        PriceResult join = CompletableFuture.supplyAsync(
                () -> HttpRequestMock.getMouXiXiPrice(product)
        ).thenCombine(
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiDiscounts(product)),
                this::computeRealPrice
        ).join();
        System.out.println(join);
    }

    /**
     * get(): 等待CompletableFuture执行完成并获取其具体执行结果，可能会抛出异常，需要代码调用的地方手动try...catch进行处理。
     * join(): 等待CompletableFuture执行完成并获取其具体执行结果，可能会抛出运行时异常，无需代码调用的地方手动try...catch进行处理。
     * 两者的区别就在于是否需要调用方显式的进行try...catch处理逻辑
     *
     * @param product
     */
    public void testGetAndJoin(String product) {
        // join无需显式try...catch...
        PriceResult joinResult = CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiPrice(product))
                .join();

        try {
            // get显式try...catch...
            PriceResult getResult = CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiPrice(product))
                    .get(5L, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 演示结合Stream的时候，到底是一个Stream中操作，还是2个Stream中操作，区别
     *
     * @param products
     * @return
     */
    public PriceResult comparePriceInOnePlat(List<String> products) {
        return products.stream()
                .map(product ->
                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                                     .thenCombine(
                                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                             this::computeRealPrice))
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();
    }

    /**
     * 演示两个map写法等同于一个map写法
     *
     * @param products
     * @return
     */
    public PriceResult comparePriceInOnePlat1(List<String> products) {
        return products.stream()
                .map(product ->
                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                                     .thenCombine(
                                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                             this::computeRealPrice)
                                     .join())
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();
    }


    /**
     * Stream分开，并行模式
     * Stream的操作具有延迟执行的特点，且只有遇到终止操作（比如collect方法）的时候才会真正的执行。
     * 从执行结果可以看出，三个商品并行处理，整体处理耗时相比前面编码方式有很大提升，达到了预期的效果。
     * @param products
     * @return
     */
    public PriceResult comparePriceInOnePlat2(List<String> products) {
        // 先触发各自平台的并行处理
        List<CompletableFuture<PriceResult>> completableFutures = products.stream()
                .map(product ->
                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                                     .thenCombine(
                                             CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                             this::computeRealPrice))
                .collect(Collectors.toList());

        // 所以遇到这种需要并行处理且需要合并多个并行处理流程的情况下，需要将并行流程与合并逻辑放到两个Stream中，这样分别触发完成各自的处理逻辑，就可以了。
        // 在独立的流中，等待所有并行处理结束，做最终结果处理
        return completableFutures.stream()
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();
    }


    public void testCreateFuture(String product) {
        // supplyAsync， 执行逻辑有返回值PriceResult
        CompletableFuture<PriceResult> supplyAsyncResult =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product));
        // runAsync, 执行逻辑没有返回值
        CompletableFuture<Void> runAsyncResult =
                CompletableFuture.runAsync(() -> System.out.println(product));
    }

    public void testStepByStep(CompletableFuture<PriceResult> supplyAsyncResult) {
        CompletableFuture<Integer> applyResult =
                supplyAsyncResult.thenApply(PriceResult::getRealPrice);
        CompletableFuture<Integer> composeResult =
                supplyAsyncResult.thenCompose(priceResult -> CompletableFuture.supplyAsync(priceResult::getPrice));
        CompletableFuture<Void> voidCompletableFuture =
                supplyAsyncResult.thenAccept(priceResult -> System.out.println(priceResult.getPrice()));
        supplyAsyncResult.thenRun(() -> {
        });

    }

    private PriceResult computeRealPrice(PriceResult priceResult, int disCounts) {
        priceResult.setRealPrice(priceResult.getPrice() - disCounts);
        LogHelper.printLog(priceResult.getPlatform() + "最终价格计算完成：" + priceResult.getRealPrice());
        return priceResult;
    }

    public PriceResult testThenApply(String product) {
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouBao =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                     this::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouDong =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongDiscounts
                                             (product)),
                                     this::computeRealPrice);

        return null;
    }

    /**
     * 执行之后会发现，supplyAsync抛出异常后，后面的thenApply并没有被执行。
     */
    @Test
    public void testExceptionHandle1() {
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("supplyAsync excetion occurred...");
        }).thenApply(obj -> {
            System.out.println("thenApply executed...");
            return obj;
        }).join();
    }

    /**
     * 再执行可以发现，即使前面环节出现异常，后面环节也可以继续处理，且可以拿到前一环节抛出的异常信息：
     */
    @Test
    public void testExceptionHandle2() {
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("supplyAsync excetion occurred...");
        }).handle((obj, e) -> {
            if (e != null) {
                System.out.println("thenApply executed, exception occurred...");
            }
            return obj;
        }).join();
    }

    @Test
    public void testCombineHandle() {
        System.out.println("开始执行");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> getResult("future1"));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "future2");
        CompletableFuture.allOf(future1, future2).join();
        System.out.println("执行完成");
    }

    private String getResult(String result) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}