package com.aweit.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aweit.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author,String>  {
    public Optional<Author> findById(String authorId);
}
