package com.navercorp.pinpoint.common.activemq.exception;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "activeMQExceptionListener")
@Conditional({ UseActiveMQForPersistence.class })
public class ExceptionListener implements javax.jms.ExceptionListener {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onException(JMSException exception) {
		logger.error("Exception caught while listening for JMS connections", exception);
	}

}
