package com.datajpa.relationship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.CityRequestDto;
import com.datajpa.relationship.model.City;

@Service
public interface CityService {

    public City addCity(CityRequestDto cityRequestDto);

    public List<City> getCities();

    public City getCity(Long cityId);

    public City deleteCity(Long cityId);

    public City editCity(Long cityId, CityRequestDto cityRequestDto);

    
}
