package com.aweit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aweit.utils.UserContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientConfiguration {

	@Bean
    public RequestInterceptor requestInterceptor() {
		return (RequestTemplate template) -> {
			template.header("Authorization", UserContextHolder.getContext().getAuthToken());
		};
	}
}
