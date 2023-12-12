package com.datajpa.relationship.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datajpa.relationship.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
