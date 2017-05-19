package com.smart.server.lock;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务的分布式锁
 * 
 * @ClassName: ScheduleTaskLock
 * @Description:TODO
 * @author: gaowenming
 * @date: 2017年5月5日 上午11:47:10
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScheduleTaskLock {

	long expiration() default 120; // 缓存过期时间

}