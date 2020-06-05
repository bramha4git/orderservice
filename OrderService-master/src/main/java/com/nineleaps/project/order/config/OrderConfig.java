package com.nineleaps.project.order.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Bean(name = "ErrorMessageSource")
	public MessageSource getMessageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		
		messageSource.setBasename("error_messages/err_message_en");
		
		return messageSource;
	}
}
