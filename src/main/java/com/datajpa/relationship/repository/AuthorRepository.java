package com.datajpa.relationship.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datajpa.relationship.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
