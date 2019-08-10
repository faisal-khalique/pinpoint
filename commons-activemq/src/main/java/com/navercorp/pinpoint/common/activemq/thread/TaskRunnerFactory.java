package com.navercorp.pinpoint.common.activemq.thread;

import java.util.concurrent.RejectedExecutionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "amqTaskRunnerFactory")
@Conditional({ UseActiveMQForPersistence.class })
public class TaskRunnerFactory extends org.apache.activemq.thread.TaskRunnerFactory {

	@Override
	@Autowired
	public void setMaxIterationsPerRun(@Value("${active.ma.max.iterations.per.run:10}") final int maxIterationsPerRun) {
		super.setMaxIterationsPerRun(maxIterationsPerRun);
	}

	@Override
	@Autowired
	public void setName(@Value("${active.mq.thread.name:pinpoint-activemq}") final String name) {
		super.setName(name);
	}

	@Override
	@Autowired
	public void setPriority(@Value("${active.mq.thread.priority:5}") final int priority) {
		super.setPriority(priority);
	}

	@Override
	@Autowired
	public void setDaemon(@Value("${active.mq.thread.daemon:true}") final boolean daemon) {
		super.setDaemon(daemon);
	}

	@Override
	@Autowired
	public void setDedicatedTaskRunner(
			@Value("${active.mq.dedicated.task.runner:true}") final boolean dedicatedTaskRunner) {
		super.setDedicatedTaskRunner(dedicatedTaskRunner);
	}

	@Override
	@Autowired
	public void setMaxThreadPoolSize(@Value("${active.mq.max.thread.pool:10}") final int maxThreadPoolSize) {
		super.setMaxThreadPoolSize(maxThreadPoolSize);
	}

	@Override
	@Autowired
	public void setRejectedTaskHandler(
			@Qualifier(value = "rejectedExecutionHandler") final RejectedExecutionHandler rejectedTaskHandler) {
		super.setRejectedTaskHandler(rejectedTaskHandler);
	}

	@Override
	@Autowired
	public void setShutdownAwaitTermination(
			@Value("${active.mq.shutdown.await.termination.millis:5000}") final long shutdownAwaitTermination) {
		super.setShutdownAwaitTermination(shutdownAwaitTermination);
	}

}
