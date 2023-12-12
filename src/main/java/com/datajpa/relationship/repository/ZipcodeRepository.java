package com.datajpa.relationship.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datajpa.relationship.model.Zipcode;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode, Long>{
    
}
