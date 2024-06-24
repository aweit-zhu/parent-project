package com.aweit.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aweit.model.Author;
import com.aweit.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
	
    @Autowired
    private AuthorRepository repository;

    public Author findById(String authorId) {
    	Optional<Author> opt = repository.findById(authorId);
        return (opt.isPresent()) ? opt.get() : null;
    }	

    public Author create(Author author){
    	author.setId( UUID.randomUUID().toString());
        author = repository.save(author);
        return author;

    }

    public void update(Author author){
    	repository.save(author);
    }

    public void delete(String authorId){
    	repository.deleteById(authorId);
    }
    
    @SuppressWarnings("unused")
	private void sleep(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
}