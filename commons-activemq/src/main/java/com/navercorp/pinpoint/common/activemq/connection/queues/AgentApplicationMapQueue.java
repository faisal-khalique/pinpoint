package com.navercorp.pinpoint.common.activemq.connection.queues;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "agentApplicationMapQueue")
@Conditional({ UseActiveMQForPersistence.class })
public class AgentApplicationMapQueue extends ActiveMQQueue {

	@Autowired
	public AgentApplicationMapQueue(
			@Value("${active.mq.queue.agent.application.map:jms.queue.pinpoint.default}") final String defaultQueueName) {
		super(defaultQueueName);
	}

}
