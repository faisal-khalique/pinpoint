package com.navercorp.pinpoint.common.activemq.connection.factory;

import java.util.concurrent.RejectedExecutionHandler;

import javax.jms.ExceptionListener;

import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.ClientInternalExceptionListener;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.navercorp.pinpoint.system.annotations.UseActiveMQForPersistence;

import lombok.Getter;

@Component(value = "amqConnectionFactory")
@Conditional({ UseActiveMQForPersistence.class })
public class ActiveMQConnectionFactory extends org.apache.activemq.ActiveMQConnectionFactory {

	@Getter
	private String brokerURL;
	@Getter
	private String userName;
	@Getter
	private String password;
	@Getter
	private String clientIDPrefix;

	@Autowired
	public ActiveMQConnectionFactory(@Value("${active.mq.broker.host:tcp://localhost:61616}") final String brokerURL,
			@Value("${active.mq.user.name:#{null}}") final String userName,
			@Value("${active.mq.user.password:#{null}}") final String password,
			@Value("${active.mq.client.id.prefix:pinpoint}") final String clientIDPrefix) {
		super(userName, password, brokerURL);
		this.brokerURL = brokerURL;
		this.userName = userName;
		this.password = password;
		this.clientIDPrefix = clientIDPrefix;
	}

	@Override
	@Autowired
	public void setCopyMessageOnSend(@Value("${active.mq.copy.message.on.send:false}") final boolean copyMessageOnSend) {
		super.setCopyMessageOnSend(copyMessageOnSend);
	}

	@Override
	@Autowired
	public void setDisableTimeStampsByDefault(@Value("${active.mq.disable.timsetamp:true}") final boolean disableTimeStampsByDefault) {
		super.setDisableTimeStampsByDefault(disableTimeStampsByDefault);
	}

	@Override
	@Autowired
	public void setOptimizedMessageDispatch(@Value("${active.mq.send.optimised.dispatch:true}") final boolean optimizedMessageDispatch) {
		super.setOptimizedMessageDispatch(optimizedMessageDispatch);
	}

	@Override
	@Autowired
	public void setPrefetchPolicy(@Qualifier(value = "activeMQPrefetchPolicy") final ActiveMQPrefetchPolicy prefetchPolicy) {
		super.setPrefetchPolicy(prefetchPolicy);
	}

	@Override
	@Autowired
	public void setUseAsyncSend(@Value("${active.mq.use.async.send:true}") final boolean useAsyncSend) {
		super.setUseAsyncSend(useAsyncSend);
	}

	@Override
	@Autowired
	public void setAlwaysSyncSend(@Value("${active.mq.always.sync.send:false}") final boolean alwaysSyncSend) {
		super.setAlwaysSyncSend(alwaysSyncSend);
	}

	@Override
	@Autowired
	public void setUseRetroactiveConsumer(@Value("${active.mq.use.retroactive.consumer:false}") final boolean useRetroactiveConsumer) {
		super.setUseRetroactiveConsumer(useRetroactiveConsumer);
	}

	@Override
	@Autowired
	public void setExclusiveConsumer(@Value("${active.mq.exclusive.consumer:false}") final boolean exclusiveConsumer) {
		super.setExclusiveConsumer(exclusiveConsumer);
	}

	@Override
	@Autowired
	public void setRedeliveryPolicy(@Qualifier(value = "amqRedeliveryPolicy") final RedeliveryPolicy redeliveryPolicy) {
		super.setRedeliveryPolicy(redeliveryPolicy);
	}

	@Override
	@Autowired
	public void setSendTimeout(@Value("${actve.mq.send.timeout.millis:5000}") final int sendTimeout) {
		super.setSendTimeout(sendTimeout);
	}

	@Override
	@Autowired
	public void setSendAcksAsync(@Value("${active.mq.send.acks.async:true}") final boolean sendAcksAsync) {
		super.setSendAcksAsync(sendAcksAsync);
	}

	@Override
	@Autowired
	public void setMessagePrioritySupported(@Value("${active.mq.message.priority.supported:true}") final boolean messagePrioritySupported) {
		super.setMessagePrioritySupported(messagePrioritySupported);
	}

	@Override
	@Autowired
	public void setUseCompression(@Value("${active.mq.use.message.compression:true}") final boolean useCompression) {
		super.setUseCompression(useCompression);
	}

	@Override
	@Autowired
	public void setObjectMessageSerializationDefered(@Value("${active.mq.message.serial.deferred:false}") final boolean objectMessageSerializationDefered) {
		super.setObjectMessageSerializationDefered(objectMessageSerializationDefered);
	}

	@Override
	@Autowired
	public void setDispatchAsync(@Value("${active.mq.dispatch.async:true}") final boolean asyncDispatch) {
		super.setDispatchAsync(asyncDispatch);
	}

	@Override
	@Autowired
	public void setCloseTimeout(@Value("${active.mq.close.timeout.millis:5000}") final int closeTimeout) {
		super.setCloseTimeout(closeTimeout);
	}

	@Override
	@Autowired
	public void setAlwaysSessionAsync(@Value("${active.mq.session.async:true}") final boolean alwaysSessionAsync) {
		super.setAlwaysSessionAsync(alwaysSessionAsync);
	}

	@Override
	@Autowired
	public void setOptimizeAcknowledge(@Value("${active.mq.optimised.acknowledge:true}") final boolean optimizeAcknowledge) {
		super.setOptimizeAcknowledge(optimizeAcknowledge);
	}

	@Override
	@Autowired
	public void setOptimizeAcknowledgeTimeOut(@Value("${active.mq.optimised.acknowledge.timeout.millis:5000}") final long optimizeAcknowledgeTimeOut) {
		super.setOptimizeAcknowledgeTimeOut(optimizeAcknowledgeTimeOut);
	}

	@Override
	@Autowired
	public void setNestedMapAndListEnabled(@Value("${active.mq.nested.maps.enabled:true}") final boolean structuredMapsEnabled) {
		super.setNestedMapAndListEnabled(structuredMapsEnabled);
	}

	@Override
	@Autowired
	public void setConnectionIDPrefix(@Value("${active.mq.connection.id.prefix:pinpoint}") final String connectionIDPrefix) {
		super.setConnectionIDPrefix(connectionIDPrefix);
	}

	@Override
	@Autowired
	public void setStatsEnabled(@Value("${active.mq.stats.enabled:false}") final boolean statsEnabled) {
		super.setStatsEnabled(statsEnabled);
	}

	@Override
	@Autowired
	public void setWarnAboutUnstartedConnectionTimeout(@Value("${active.mq.connection.start.timeout.millis:5000}") final long warnAboutUnstartedConnectionTimeout) {
		super.setWarnAboutUnstartedConnectionTimeout(warnAboutUnstartedConnectionTimeout);
	}

	@Override
	@Autowired
	public void setExceptionListener(@Qualifier(value = "activeMQExceptionListener") final ExceptionListener exceptionListener) {
		super.setExceptionListener(exceptionListener);
	}

	@Override
	@Autowired
	public void setUseDedicatedTaskRunner(@Value("${active.mq.dedicated.task.runner:true}") final boolean useDedicatedTaskRunner) {
		super.setUseDedicatedTaskRunner(useDedicatedTaskRunner);
	}

	@Override
	@Autowired
	public void setConsumerFailoverRedeliveryWaitPeriod(@Value("${active.mq.consumer.redelivery.wait.millis:5000}") final long consumerFailoverRedeliveryWaitPeriod) {
		super.setConsumerFailoverRedeliveryWaitPeriod(consumerFailoverRedeliveryWaitPeriod);
	}

	@Override
	@Autowired
	public void setClientInternalExceptionListener(@Qualifier(value = "clientInternalExceptionListener") final ClientInternalExceptionListener clientInternalExceptionListener) {
		super.setClientInternalExceptionListener(clientInternalExceptionListener);
	}

	@Override
	@Autowired
	public void setCheckForDuplicates(@Value("${active.mq.check.duplicates:false}") final boolean checkForDuplicates) {
		super.setCheckForDuplicates(checkForDuplicates);
	}

	@Override
	@Autowired
	public void setTransactedIndividualAck(@Value("${active.mq.transacted.individual.ack:true}") final boolean transactedIndividualAck) {
		super.setTransactedIndividualAck(transactedIndividualAck);
	}

	@Override
	@Autowired
	public void setNonBlockingRedelivery(@Value("${active.mq.non.blocking.redelivery:true}") final boolean nonBlockingRedelivery) {
		super.setNonBlockingRedelivery(nonBlockingRedelivery);
	}

	@Override
	@Autowired
	public void setMaxThreadPoolSize(@Value("${active.mq.max.thread.pool:10}") final int maxThreadPoolSize) {
		super.setMaxThreadPoolSize(maxThreadPoolSize);
	}

	@Override
	@Autowired
	public void setConsumerExpiryCheckEnabled(@Value("${active.mq.check.message.expiry:false}") final boolean consumerExpiryCheckEnabled) {
		super.setConsumerExpiryCheckEnabled(consumerExpiryCheckEnabled);
	}

	@Override
	@Autowired
	public void setTrustAllPackages(@Value("${active.mq.trust.all.packages:true}") final boolean trustAllPackages) {
		super.setTrustAllPackages(trustAllPackages);
	}

	@Override
	public void setClientIDPrefix(String clientIDPrefix) {
		super.setClientIDPrefix(clientIDPrefix);
		this.clientIDPrefix = clientIDPrefix;
	}

	@Override
	@Autowired
	public void setSessionTaskRunner(@Qualifier(value = "amqTaskRunnerFactory") final TaskRunnerFactory sessionTaskRunner) {
		super.setSessionTaskRunner(sessionTaskRunner);
	}

	@Override
	@Autowired
	public void setRejectedTaskHandler(@Qualifier(value = "rejectedExecutionHandler") final RejectedExecutionHandler rejectedTaskHandler) {
		super.setRejectedTaskHandler(rejectedTaskHandler);
	}

	@Override
	@Autowired
	public void setOptimizedAckScheduledAckInterval(@Value("${active.mq.optimized.ack.scheduled.interval.millis:5000}") final long optimizedAckScheduledAckInterval) {
		super.setOptimizedAckScheduledAckInterval(optimizedAckScheduledAckInterval);
	}

	@Override
	@Autowired
	public void setRmIdFromConnectionId(@Value("${active.mq.rmid.for.connectionid:true}") final boolean rmIdFromConnectionId) {
		super.setRmIdFromConnectionId(rmIdFromConnectionId);
	}

	@Override
	@Autowired
	public void setConnectResponseTimeout(@Value("${active.mq.connect.response.timeout.millis:5000}") final int connectResponseTimeout) {
		super.setConnectResponseTimeout(connectResponseTimeout);
	}

}
