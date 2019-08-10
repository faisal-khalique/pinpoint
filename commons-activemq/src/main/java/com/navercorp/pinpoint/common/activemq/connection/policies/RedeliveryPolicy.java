package com.navercorp.pinpoint.common.activemq.connection.policies;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Component(value = "amqRedeliveryPolicy")
@Conditional({UseActiveMQForPersistence.class})
public class RedeliveryPolicy extends org.apache.activemq.RedeliveryPolicy {

	private static final long serialVersionUID = 2175207681895116797L;

}
