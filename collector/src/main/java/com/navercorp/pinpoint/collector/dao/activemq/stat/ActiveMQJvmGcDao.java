/*
 * Copyright 2016 Naver Corp.
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

package com.navercorp.pinpoint.collector.dao.activemq.stat;

import java.util.List;

import javax.jms.Destination;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import com.navercorp.pinpoint.collector.dao.AbstractJacksonSerializer;
import com.navercorp.pinpoint.collector.dao.AgentStatDaoV2;
import com.navercorp.pinpoint.common.server.bo.stat.JvmGcBo;
import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Repository
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQJvmGcDao extends AbstractJacksonSerializer implements AgentStatDaoV2<JvmGcBo> {

	@Autowired
	@Qualifier(value = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "jVMGCQueue")
	private Destination destination;

	@Override
	public void insert(String agentId, List<JvmGcBo> jvmGcBos) {
		if (agentId == null) {
			throw new NullPointerException("agentId must not be null");
		}
		if (CollectionUtils.isEmpty(jvmGcBos)) {
			return;
		}
		String jsonString = serialize(jvmGcBos);
		if (jsonString != null) {
			jmsTemplate.convertAndSend(destination, jsonString);
		}
	}
}
