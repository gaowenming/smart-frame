package com.smart.server.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 基于Async注解的异步任务，需要开启@EnableAsync
 * Author: gaowenming
 * Description:
 * Date: Created in 20:49 2017/7/2.
 */
@Component
public class AsyncTask {
    public static Random random = new Random();

    @Async
    public Future<String> task1() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> task2() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> task3() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }

    @Async("smartExecutor")
    public void taskAsync1() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println("##########################" + i);
        }

    }

    @Async("smartExecutor")
    public void taskAsync2() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println("************************" + i);
        }
    }
}
