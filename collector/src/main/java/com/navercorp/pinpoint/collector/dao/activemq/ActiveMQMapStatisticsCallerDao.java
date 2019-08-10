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
import com.navercorp.pinpoint.collector.dao.MapStatisticsCallerDao;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Repository
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQMapStatisticsCallerDao extends AbstractJacksonSerializer implements MapStatisticsCallerDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier(value = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "agentCallerStatisticsQueue")
	private Destination destination;

	@Override
	public void update(String callerApplicationName, ServiceType callerServiceType, String callerAgentid,
			String calleeApplicationName, ServiceType calleeServiceType, String calleeHost, int elapsed,
			boolean isError) {
		if (callerApplicationName == null) {
			throw new NullPointerException("callerApplicationName must not be null");
		}
		if (calleeApplicationName == null) {
			throw new NullPointerException("calleeApplicationName must not be null");
		}
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("callerApplicationName", callerApplicationName);
		dataMap.put("callerServiceType", callerServiceType);
		dataMap.put("callerAgentid", callerAgentid);
		dataMap.put("calleeApplicationName", calleeApplicationName);
		dataMap.put("calleeServiceType", calleeServiceType);
		dataMap.put("calleeHost", calleeHost);
		dataMap.put("elapsed", elapsed);
		dataMap.put("isError", isError);
		String jsonString = serialize(dataMap);
		if (logger.isDebugEnabled()) {
			logger.debug("[Caller] {} ({}) {} -> {} ({})[{}]", callerApplicationName, callerServiceType, callerAgentid,
					calleeApplicationName, calleeServiceType, calleeHost);
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