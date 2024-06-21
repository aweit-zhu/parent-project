package com.aweit.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aweit.model.Author;


@Component
public class AuthorRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Author getAuthor(String authorId){
        ResponseEntity<Author> restExchange =
                restTemplate.exchange(
                        "http://author-service/v1/author/{authorId}",
                        HttpMethod.GET,
                        null, Author.class, authorId);

        return restExchange.getBody();
    }
}