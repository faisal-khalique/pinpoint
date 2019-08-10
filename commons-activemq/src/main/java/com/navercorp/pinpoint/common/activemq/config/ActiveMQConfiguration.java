package com.navercorp.pinpoint.common.activemq.config;

//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
//import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//import com.navercorp.pinpoint.common.annotations.util.DBInUseActiveMQ;

//@Configuration
//@PropertySources({ @PropertySource(value = "classpath:activemq.properties", ignoreResourceNotFound = false) })
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Conditional({ DBInUseActiveMQ.class })
/**
 * @author khalique.faisal@gmail.com
 * Ok. so this class doesn't work. and it creates havoc!
 * Please refer to the links at <ul>
 * 	<li><a href="https://stackoverflow.com/questions/28369582/spring-boot-spring-always-assigns-default-value-to-property-despite-of-it-bein">StackOverFlow Question</a></li>
 *  <li><a href="https://github.com/spring-projects/spring-framework/issues/14623">SPR-9989</a></li>
 *  </ul>
 */
@Deprecated
public class ActiveMQConfiguration {
	
	//@Bean
	@Deprecated
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
