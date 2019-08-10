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
import com.navercorp.pinpoint.collector.dao.TraceDao;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanChunkBo;
import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

@Repository
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQTraceDaoV2 extends AbstractJacksonSerializer implements TraceDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier(value = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "agentApplicationTraceQueue")
	private Destination destination;

	@Override
	public boolean insert(final SpanBo spanBo) {
		if (spanBo == null) {
			throw new NullPointerException("spanBo must not be null");
		}
		String jsonString = serialize(spanBo);
		if (logger.isDebugEnabled()) {
			logger.debug("insert event. {}", jsonString);
		}
		if (jsonString != null) {
			jmsTemplate.convertAndSend(destination, jsonString);
		}
		return true;
	}

	@Override
	public boolean insertSpanChunk(SpanChunkBo spanChunkBo) {
		if (spanChunkBo != null) {
			String jsonString = serialize(spanChunkBo);
			if (logger.isDebugEnabled()) {
				logger.debug("insert event. {}", jsonString);
			}
			if (jsonString != null) {
				jmsTemplate.convertAndSend(destination, jsonString);
			}
		}
		return true;
	}
}
