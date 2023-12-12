package com.datajpa.relationship.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datajpa.relationship.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
    
}
