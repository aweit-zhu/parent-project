package com.aweit.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aweit.model.Author;
import com.aweit.service.AuthorService;

@RestController
@RequestMapping(value="v1/author")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping(value="/any")
    public ResponseEntity<String> testAnyRole() {
        return ResponseEntity.ok("Can be accessed by role admin or user!");
    }
    @RolesAllowed({ "ADMIN"})
    @GetMapping(value="/admin")
    public ResponseEntity<String> testRoleAdmin() {
    	return ResponseEntity.ok("Can only be accessed by role admin!");
    }
    @RolesAllowed({ "USER" })
    @GetMapping(value="/user")
    public ResponseEntity<String> testRoleUser() {
    	return ResponseEntity.ok("Can only be accessed by role user!");
    }

    @RolesAllowed({ "ADMIN", "USER" })  
    @RequestMapping(value="/{authorId}",method = RequestMethod.GET)
    public ResponseEntity<Author> getAuthor( @PathVariable("authorId") String authorId) {
        return ResponseEntity.ok(service.findById(authorId));
    }

    @RolesAllowed({ "ADMIN", "USER" }) 
    @RequestMapping(value="/{authorId}",method = RequestMethod.PUT)
    public void updateAuthor( @PathVariable("authorId") String id, @RequestBody Author author) {
        service.update(author);
    }

    @RolesAllowed({ "ADMIN", "USER" }) 
    @PostMapping
    public ResponseEntity<Author>  saveAuthor(@RequestBody Author author) {
    	return ResponseEntity.ok(service.create(author));
    }

    @RolesAllowed("ADMIN")    
    @DeleteMapping(value="/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuthor(@PathVariable("authorId") String authorId) {
		service.delete(authorId);
	}

}