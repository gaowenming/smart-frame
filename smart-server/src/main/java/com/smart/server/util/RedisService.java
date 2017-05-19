package com.smart.server.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * redis操作工具类
 * 
 * @author gaowenming
 *
 */
@Service
public class RedisService {
	private Logger logger = LoggerFactory.getLogger(RedisService.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public final int ONE_DAY = 60 * 60 * 24;
	public final int FIVE_MINUTES = 5 * 60;
	public final int ONE_HOUR = 60 * 60;

	/**
	 * 放入缓存，有失效时间
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 *            失效时间（秒）
	 * @return
	 */
	public boolean addCache(String key, Object value, int expireTime) {
		if (StringUtils.isEmpty(key) || value == null || expireTime <= 0) {
			return false;
		}
		try {
			redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(String.format("redis set operation for value failed by key [%s]: %s", key, e.getMessage()));
		}

		return true;
	}

	/**
	 * 永久有效
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addCache(String key, Object value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			logger.error(String.format("redis set operation for value failed by key [%s]: %s", key, e.getMessage()));
		}

		return true;
	}

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {

		if (StringUtils.isEmpty(key)) {
			return null;
		}
		Object object = null;
		try {
			object = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			logger.error(String.format("redis get operation for value failed by key [%s]: %s", key, e.getMessage()));
			return object;
		}
		return object;
	}

	/**
	 * 获取缓存值(String)
	 * 
	 * @param key
	 * @return
	 */
	public String getCacheString(String key) {

		if (StringUtils.isEmpty(key)) {
			return "";
		}
		Object object = null;
		try {
			object = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			logger.error(String.format("redis get operation for value failed by key [%s]: %s", key, e.getMessage()));
			return "";
		}
		if (object != null) {
			return object.toString();
		}
		return "";
	}

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCacheMap(String key) {

		if (StringUtils.isEmpty(key)) {
			return null;
		}
		Map<String, Object> map = null;
		try {
			Object object = redisTemplate.opsForValue().get(key);
			if (object != null) {
				map = (Map<String, Object>) object;
			}
		} catch (Exception e) {
			logger.error(String.format("redis get operation for value failed by key [%s]: %s", key, e.getMessage()));
			return map;
		}
		return map;
	}

	/**
	 * 删除key对应的缓存
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeKey(String key) {
		boolean b = false;
		if (StringUtils.isEmpty(key)) {
			return b;
		}
		try {
			redisTemplate.delete(key);
			b = true;
		} catch (Exception e) {
			logger.error(String.format("redis removeKey for key failed by key [%s]: %s", key, e.getMessage()));
		}

		return b;
	}

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public String flushDB() {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	/**
	 * 查看redis里有多少数据
	 */
	public long dbSize() {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * 批量删除
	 * 
	 * @param keys
	 * @return
	 */
	public boolean delete(final Collection<String> keys) {
		boolean b = false;
		if (keys.isEmpty()) {
			return b;
		}
		try {
			redisTemplate.delete(keys);
			b = true;
		} catch (Exception e) {
			logger.error(String.format("redis removeKey for key failed by key [%s]: %s", keys, e.getMessage()));
		}

		return b;
	}

	/**
	 * 批量多个key
	 * 
	 * @param keys
	 * @return
	 */
	public void delete(final String... keys) {
		Collection<String> cols = new ArrayList<String>();
		for (String key : keys) {
			cols.add(key);
		}
		delete(cols);
	}

	/**
	 * 如果原先的string里有数据，则使用此方法set新数据会失败，并返回false
	 * 
	 * @Title: setNXBoolean
	 * @Description:
	 * @param key
	 * @param value
	 * @return
	 * 
	 * 		boolean
	 */
	public boolean setNXBoolean(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	/**
	 * 让key失效
	 * 
	 * @Title: expire
	 * @Description:
	 * @param key
	 * @param seconds
	 * @return
	 * 
	 */
	public long expire(String key, int seconds) {
		if (key == null || key.equals("")) {
			return 0;
		}
		try {
			redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
		} catch (Exception ex) {
			logger.error("EXPIRE error[key=" + key + " seconds=" + seconds + "]" + ex.getMessage(), ex);
		}
		return 0;
	}

	/**
	 * 先获取原先存在的数据，再添加数据覆盖原先的
	 * 
	 * @Title: getSet
	 * @Description:
	 * @param key
	 * @param value
	 * @param clazz
	 * @return
	 * 
	 */
	public Long getSetLong(final String key, Object value) {
		Object oldValue = redisTemplate.opsForValue().getAndSet(key, value);
		if (oldValue == null || StringUtils.isEmpty(oldValue.toString())) {
			return null;
		}
		return Long.valueOf(oldValue.toString());
	}

	/**
	 * 
	 * (这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author gaowenming
	 * @param key
	 * @param value
	 * @return
	 * @since JDK 1.8
	 */
	public Object getSetObject(final String key, Object value) {
		Object oldValue = redisTemplate.opsForValue().getAndSet(key, value);
		return oldValue;
	}

}
