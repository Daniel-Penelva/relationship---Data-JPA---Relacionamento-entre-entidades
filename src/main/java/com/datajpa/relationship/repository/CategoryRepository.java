package com.datajpa.relationship.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datajpa.relationship.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
    
}
