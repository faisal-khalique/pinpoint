/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.collector.dao.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import com.navercorp.pinpoint.collector.dao.AbstractJacksonSerializer;
import com.navercorp.pinpoint.collector.dao.MapResponseTimeDao;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Repository
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQMapResponseTimeDao extends AbstractJacksonSerializer implements MapResponseTimeDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier(value = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "agentResponseTimeQueue")
	private Destination destination;

	@Override
	public void received(String applicationName, ServiceType applicationServiceType, String agentId, int elapsed,
			boolean isError) {
		if (applicationName == null) {
			throw new NullPointerException("applicationName must not be null");
		}
		if (agentId == null) {
			throw new NullPointerException("agentId must not be null");
		}
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("applicationName", applicationName);
		dataMap.put("applicationServiceType", applicationServiceType);
		dataMap.put("agentId", agentId);
		dataMap.put("elapsed", elapsed);
		dataMap.put("isError", isError);
		String jsonString = serialize(dataMap);
		if (logger.isDebugEnabled()) {
			logger.debug("[Received] {} ({})[{}]", applicationName, applicationServiceType, agentId);
		}
		if (jsonString != null) {
			jmsTemplate.convertAndSend(destination, jsonString);
		}
	}

	@Override
	public void flushAll() {
		// nothing to do for now
	}
}
