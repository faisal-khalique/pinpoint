package com.navercorp.pinpoint.common.activemq.connection.queues;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "defaultDestination")
@Conditional({ UseActiveMQForPersistence.class })
public class DefaultQueue extends ActiveMQQueue {

	@Autowired
	public DefaultQueue(
			@Value("${active.mq.default.destination:jms.queue.default.pinpoint}") final String defaultQueueName) {
		super(defaultQueueName);
	}

}
