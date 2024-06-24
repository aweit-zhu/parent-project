package com.aweit.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aweit.config.FeignClientConfiguration;
import com.aweit.model.Author;

@FeignClient(name = "author-service", configuration = FeignClientConfiguration.class)
public interface AuthorFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/author/{authorId}", consumes = "application/json")
	Author getAuthor(@PathVariable("authorId") String authorId);
}
