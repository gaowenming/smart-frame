package com.smart.server.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于Redis注解的分布式锁
 * 
 * @ClassName: RedisLockable
 * @Description:TODO
 * @author: gaowenming
 * @date: 2017年5月5日 上午11:47:10
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLockable {

	long expiration() default 120; // 缓存过期时间

	int retryCount() default 3; // 锁等待重试次数，-1为一直重试,知道拿到锁为止
}