package com.navercorp.pinpoint.common.activemq.exception;

import org.apache.activemq.ClientInternalExceptionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "clientInternalExceptionListener")
@Conditional({UseActiveMQForPersistence.class})
public class ClientInternalExceptionListenerImpl implements ClientInternalExceptionListener {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onException(Throwable exception) {
		logger.error("Client connection exception", exception);
	}

}
