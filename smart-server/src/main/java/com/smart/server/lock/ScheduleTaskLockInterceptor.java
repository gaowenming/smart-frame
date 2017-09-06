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

import lombok.extern.slf4j.Slf4j;

/**
 * redis锁注解拦截器
 * 
 * @ClassName: ScheduleTaskLockInterceptor
 * @Description:TODO
 * @author: gaowenming
 * @date: 2017年5月5日 下午1:42:33
 *
 */
@Aspect
@Component
@Slf4j
public class ScheduleTaskLockInterceptor {

	@Autowired
	private RedisService redisService;

	@Pointcut("@annotation(com.smart.server.lock.ScheduleTaskLock)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method targetMethod = AopUtils.getMostSpecificMethod(methodSignature.getMethod(), point.getTarget().getClass());
		String targetName = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();

		ScheduleTaskLock scheduleTaskLock = targetMethod.getAnnotation(ScheduleTaskLock.class);
		long expire = scheduleTaskLock.expiration();
		String redisKey = getLockKey(scheduleTaskLock, targetMethod, targetName, methodName);
		log.info("<------------------redisKey = {} ---------------->",redisKey);
		boolean isLock;

		isLock = noWaitingLock(redisKey, expire);
		log.info("<-------------------获取非等待锁---------------->isLock={}" , isLock);
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
	 * @Description:
	 * @param scheduleTaskLock
	 * @param targetMethod
	 * @param targetName
	 * @param methodName
	 * @return
	 * 
	 * 		String
	 */
	private String getLockKey(ScheduleTaskLock scheduleTaskLock, Method targetMethod, String targetName, String methodName) {
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
	 * 删除缓存
	 * 
	 * @Title: unLock
	 * @Description:
	 * @param key
	 * 
	 *            void
	 */
	private void unLock(String key) {
		redisService.delete(key);
	}

}