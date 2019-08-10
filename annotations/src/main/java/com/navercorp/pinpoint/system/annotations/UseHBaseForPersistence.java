package com.navercorp.pinpoint.system.annotations;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author khalique.faisal@gmail.com
 * @since 10th August, 2019
 * @description Created this class for usage of different persistence component
 *              for an attempt to see if we can use other persistence unit like
 *              'InfluxDB' (appears better) or 'ActiveMQ' (don't know why I am
 *              doing ActiveMQ). Default is to use HBase. Can be controlled by
 *              setting the system property {@code persistance.component} to
 *              'HBASE' (ignore case) to use HBASE (as an explicit mention)
 */
public class UseHBaseForPersistence implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return System.getProperty("persistance.component", "HBASE").equalsIgnoreCase("HBASE");
	}

}
