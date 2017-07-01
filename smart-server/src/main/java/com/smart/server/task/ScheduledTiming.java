package com.smart.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smart.server.lock.ScheduleTaskLock;

/**
 * Date: 2017年5月18日 下午8:31:36 <br/>
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
public class ScheduledTiming {

	private final static Logger logger = LoggerFactory.getLogger(ScheduledTiming.class);

	//@Scheduled(fixedRate = 60000)
	//@ScheduleTaskLock
	public void myTask() throws Exception {
		logger.info("...........定时任务开始执行........");
	}
}
