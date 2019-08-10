package com.navercorp.pinpoint.common.activemq.exception;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "rejectedExecutionHandler")
@Conditional({ UseActiveMQForPersistence.class })
public class RejectedExecutionHandler implements java.util.concurrent.RejectedExecutionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor taskExecutor) {
		logger.error("Task \"{0}\" failed to be executed by executor \"{1}\"", runnable, taskExecutor);
	}

}
