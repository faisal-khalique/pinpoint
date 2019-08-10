package com.navercorp.pinpoint.common.activemq.connection.queues;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "transactionQueue")
@Conditional({ UseActiveMQForPersistence.class })
public class TransactionQueue extends ActiveMQQueue {

	@Autowired
	public TransactionQueue(
			@Value("${active.mq.queue.transaction:jms.queue.pinpoint.default}") final String defaultQueueName) {
		super(defaultQueueName);
	}

}
