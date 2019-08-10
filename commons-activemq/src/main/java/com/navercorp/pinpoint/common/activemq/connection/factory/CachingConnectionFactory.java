package com.navercorp.pinpoint.common.activemq.connection.factory;

import javax.jms.ExceptionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "cachingConnectionFactory")
@Conditional({UseActiveMQForPersistence.class})
public class CachingConnectionFactory extends org.springframework.jms.connection.CachingConnectionFactory {
	
	@Autowired
	public CachingConnectionFactory(@Qualifier(value = "amqConnectionFactory") ActiveMQConnectionFactory activeMQCF) {
		super(activeMQCF);
	}

	@Override
	@Autowired
	public void setSessionCacheSize(@Value("${active.mq.cache.connection.size:10}") final int sessionCacheSize) {
		super.setSessionCacheSize(sessionCacheSize);
	}

	@Override
	@Autowired
	public void setCacheProducers(@Value("${active.mq.cache.producers:true}") final boolean cacheProducers) {
		super.setCacheProducers(cacheProducers);
	}

	@Override
	@Autowired
	public void setCacheConsumers(@Value("${active.mq.cache.consumers:true}") final boolean cacheConsumers) {
		super.setCacheConsumers(cacheConsumers);
	}

	@Override
	@Autowired
	public void setClientId(@Value("${active.mq.client.id.prefix:pinpoint}") final String clientId) {
		super.setClientId(clientId);
	}

	@Override
	@Autowired
	public void setExceptionListener(@Qualifier(value = "activeMQExceptionListener") final ExceptionListener exceptionListener) {
		super.setExceptionListener(exceptionListener);
	}

	@Override
	@Autowired
	public void setReconnectOnException(@Value("${active.mq.connection.reconnect.on.exception:true}") final boolean reconnectOnException) {
		super.setReconnectOnException(reconnectOnException);
	}
}