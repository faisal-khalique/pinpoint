package com.navercorp.pinpoint.common.activemq.template.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "jmsTemplate")
@Conditional({ UseActiveMQForPersistence.class })
public class JMSTemplate extends JmsTemplate {

	@Override
	@Autowired
	public void setDefaultDestination(@Qualifier(value = "defaultDestination") final Destination destination) {
		super.setDefaultDestination(destination);
	}

	@Override
	@Autowired
	public void setMessageIdEnabled(@Value("${active.mq.message.id.enabled:false}") final boolean messageIdEnabled) {
		super.setMessageIdEnabled(messageIdEnabled);
	}

	@Override
	@Autowired
	public void setMessageTimestampEnabled(
			@Value("${active.mq.message.timestamp.enabled:false}") final boolean messageTimestampEnabled) {
		super.setMessageTimestampEnabled(messageTimestampEnabled);
	}

	@Override
	@Autowired
	public void setReceiveTimeout(@Value("${active.mq.receive.timeout.millis:5000}") final long receiveTimeout) {
		super.setReceiveTimeout(receiveTimeout);
	}

	@Override
	@Autowired
	public void setDeliveryDelay(@Value("${active.mq.delivery.delay:-1}") final long deliveryDelay) {
		super.setDeliveryDelay(deliveryDelay);
	}

	@Override
	@Autowired
	public void setExplicitQosEnabled(
			@Value("${active.mq.explicit.qos.enabled:false}") final boolean explicitQosEnabled) {
		super.setExplicitQosEnabled(explicitQosEnabled);
	}

	@Override
	@Autowired
	public void setDeliveryPersistent(
			@Value("${active.mq.delivery.persistent:true}") final boolean deliveryPersistent) {
		super.setDeliveryPersistent(deliveryPersistent);
	}

	@Override
	@Autowired
	public void setDeliveryMode(@Value("${active.mq.delivery.mode:2}") final int deliveryMode) {
		super.setDeliveryMode(deliveryMode);
	}

	@Override
	@Autowired
	public void setPriority(@Value("${active.mq.priority:5}") final int priority) {
		super.setPriority(priority);
	}

	@Override
	@Autowired
	public void setTimeToLive(@Value("${active.mq.time.to.live:0}") final long timeToLive) {
		super.setTimeToLive(timeToLive);
	}

	@Override
	@Autowired
	public void setConnectionFactory(
			@Qualifier(value = "cachingConnectionFactory") final ConnectionFactory connectionFactory) {
		super.setConnectionFactory(connectionFactory);
	}

	@Override
	@Autowired
	public void setSessionAcknowledgeMode(@Value("${active.mq.session.ack.mode:1}") final int sessionAcknowledgeMode) {
		super.setSessionAcknowledgeMode(sessionAcknowledgeMode);
	}

	@Override
	@Autowired
	public void setSessionTransacted(
			@Value("${active.mq.transaction.transacted:false}") final boolean sessionTransacted) {
		super.setSessionTransacted(sessionTransacted);
	}

}
