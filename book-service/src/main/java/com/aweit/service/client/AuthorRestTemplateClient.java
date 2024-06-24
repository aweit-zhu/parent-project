package com.aweit.service.client;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aweit.model.Author;


@Component
public class AuthorRestTemplateClient {
    @Autowired
    KeycloakRestTemplate restTemplate;

    public Author getAuthor(String authorId){
        ResponseEntity<Author> restExchange =
                restTemplate.exchange(
                		 "http://gateway:8072/author/v1/author/{authorId}",
                        HttpMethod.GET,
                        null, Author.class, authorId);

        return restExchange.getBody();
    }
}
