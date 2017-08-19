package com.smart.server.task;

import com.smart.server.lock.ScheduleTaskLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2017年5月18日 下午8:31:36 <br/>
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
@Slf4j
public class ScheduledTiming {

	@Scheduled(fixedRate = 600000)
	@ScheduleTaskLock
	public void myTask() throws Exception {
		log.info("...........定时任务开始执行........");
	}
}
