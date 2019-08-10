package com.navercorp.pinpoint.common.activemq.connection.policies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "activeMQPrefetchPolicy")
@Conditional({UseActiveMQForPersistence.class})
public class ActiveMQPrefetchPolicy extends org.apache.activemq.ActiveMQPrefetchPolicy {

	private static final long serialVersionUID = -1018447242838998131L;

	@Override
	@Autowired
	public void setDurableTopicPrefetch(@Value("${active.mq.durable.topic.prefetch.size:32767}") final int durableTopicPrefetch) {
		super.setDurableTopicPrefetch(durableTopicPrefetch);
	}

	@Override
	@Autowired
	public void setQueuePrefetch(@Value("${active.mq.queue.prefetch.size:1000}") final int queuePrefetch) {
		super.setQueuePrefetch(queuePrefetch);
	}

	@Override
	@Autowired
	public void setQueueBrowserPrefetch(@Value("${active.mq.queue.browser.prefetch.size:500}") final int queueBrowserPrefetch) {
		super.setQueueBrowserPrefetch(queueBrowserPrefetch);
	}

	@Override
	@Autowired
	public void setTopicPrefetch(@Value("${active.mq.topic.prefetch.size:32767}") final int topicPrefetch) {
		super.setTopicPrefetch(topicPrefetch);
	}

	@Override
	@Autowired
	public void setOptimizeDurableTopicPrefetch(@Value("${active.mq.optimised.durable.topic.prefetch.size:1000}") final int optimizeAcknowledgePrefetch) {
		super.setOptimizeDurableTopicPrefetch(optimizeAcknowledgePrefetch);
	}

	@Override
	@Autowired
	public void setMaximumPendingMessageLimit(@Value("${active.mq.maximum.pending.message:1000}") final int maximumPendingMessageLimit) {
		super.setMaximumPendingMessageLimit(maximumPendingMessageLimit);
	}

}
