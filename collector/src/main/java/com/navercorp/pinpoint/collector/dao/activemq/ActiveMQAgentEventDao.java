/*
 * Copyright 2015 NAVER Corp.
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

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import com.navercorp.pinpoint.collector.dao.AbstractJacksonSerializer;
import com.navercorp.pinpoint.collector.dao.AgentEventDao;
import com.navercorp.pinpoint.common.server.bo.event.AgentEventBo;
import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Repository
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQAgentEventDao extends AbstractJacksonSerializer implements AgentEventDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier(value = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "agentEventQueue")
	private Destination destination;

	@Override
	public void insert(AgentEventBo agentEventBo) {
		if (agentEventBo == null) {
			throw new NullPointerException("agentEventBo must not be null");
		}
		String jsonString = serialize(agentEventBo);
		if (logger.isDebugEnabled()) {
			logger.debug("insert event. {}", jsonString);
		}
		if (jsonString != null) {
			jmsTemplate.convertAndSend(destination, jsonString);
		}
	}

}