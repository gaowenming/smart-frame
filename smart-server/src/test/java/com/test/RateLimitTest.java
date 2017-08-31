package com.test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimitTest {
    public static void main(String[] args) {
        testRateLimiter();
        // testRate();
    }

    /**
     * RateLimiter限制线程数
     */
    public static void testRateLimiter() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        RateLimiter limiter = RateLimiter.create(10.0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            limiter.acquire();
            executorService.execute(new Task("is " + i));
        }
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start) + "ms");
    }

    static class Task implements Runnable {
        String str;

        public Task(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println("call execute.." + str);
        }
    }

    public static void testRate() {
        RateLimiter limiter = RateLimiter.create(2);
        while (true) {
            System.out.println(limiter.getRate());
            limiter.acquire();
            System.out.println(new Date());
        }
    }

}