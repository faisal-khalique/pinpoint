package com.navercorp.pinpoint.collector.dao;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class AbstractJacksonSerializer {

	private Logger logger = LoggerFactory.getLogger(AbstractJacksonSerializer.class.getName());

	protected final ObjectMapper objectMapper;

	public AbstractJacksonSerializer() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US));
		objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
		objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}

	protected String serialize(Object theObject) {
		try {
			return objectMapper.writeValueAsString(theObject);
		} catch (JsonProcessingException serializationException) {
			logger.error("Failed to serialize the object of type " + theObject.getClass().getName(),
					serializationException);
			return null;
		}
	}
}
