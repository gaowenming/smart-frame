package com.smart.server.lock;

import com.smart.server.base.RedisService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * redis锁注解拦截器
 * 
 * @ClassName: RedisLockInterceptor
 * @Description:TODO
 * @author: gaowenming
 * @date: 2017年5月5日 下午1:42:33
 *
 */
@Aspect
@Component
@Slf4j
public class RedisLockInterceptor {

	@Autowired
	private RedisService redisService;

	@Pointcut("@annotation(com.smart.server.lock.RedisLockable)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method targetMethod = AopUtils.getMostSpecificMethod(methodSignature.getMethod(), point.getTarget().getClass());
		String targetName = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		Object target = point.getTarget();
		Object[] arguments = point.getArgs();

		RedisLockable redisLock = targetMethod.getAnnotation(RedisLockable.class);
		long expire = redisLock.expiration();
		String redisKey = getLockKey(redisLock, targetMethod, targetName, methodName, target, arguments);
		log.info("<------------------redisKey:{} ---------------->",redisKey);
		boolean isLock;

		isLock = waitingLock(redisKey, expire, redisLock.retryCount());
		log.info("<------------------获取等待锁---------------->isLock={}" , isLock);

		if (isLock) {
			long startTime = System.currentTimeMillis();
			try {
				return point.proceed();
			} finally {
				long parseTime = System.currentTimeMillis() - startTime;
				if (parseTime <= expire * 1000) {
					unLock(redisKey);
				}
			}
		} else {
			log.info("获取锁超时，请稍后再试");
			return null;
		}
	}

	/**
	 * 
	 * 获取缓存的key值
	 * 
	 * @Title: getLockKey
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param redisLock
	 * @param targetMethod
	 * @param targetName
	 * @param methodName
	 * @param target
	 * @param arguments
	 * @return
	 * 
	 * 		String
	 */
	private String getLockKey(RedisLockable redisLock, Method targetMethod, String targetName, String methodName, Object target, Object[] arguments) {
		StringBuilder sb = new StringBuilder("lock.");
		sb.append(targetName).append(".").append(methodName);
		return sb.toString();
	}

	/**
	 * 加锁
	 *
	 * @param key
	 *            redis key
	 * @param expire
	 *            过期时间，单位秒
	 * @return true:加锁成功，false，加锁失败
	 */
	private boolean noWaitingLock(String key, long expire) {

		long value = System.currentTimeMillis() + expire * 1000;
		boolean status = redisService.setNXBoolean(key, value);
		if (status) {
			return true;
		}

		String cacheTime = redisService.getCacheString(key);
		if (StringUtils.isEmpty(cacheTime)) {
			return true;
		}

		long oldExpireTime = Long.valueOf(cacheTime);
		if (oldExpireTime < System.currentTimeMillis()) {
			// 超时
			long newExpireTime = System.currentTimeMillis() + expire * 1000;
			Long currentExpireTime = redisService.getSetLong(key, newExpireTime);
			if (currentExpireTime == null) {
				return true;
			}
			if (currentExpireTime.longValue() == oldExpireTime) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 等待锁
	 *
	 * @param key
	 *            redis key
	 * @param expire
	 *            过期时间，单位秒
	 * @return true:加锁成功，false，加锁失败
	 */
	private boolean waitingLock(String key, long expire, int retryCount) {
		int count = 0;
		while (retryCount == -1 || count <= retryCount) {
			if (noWaitingLock(key, expire)) {
				return true;
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		return false;
	}

	private void unLock(String key) {
		redisService.delete(key);
	}

}