package com.datajpa.relationship.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.CityRequestDto;
import com.datajpa.relationship.model.City;
import com.datajpa.relationship.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    //create city (criar cidade)
    @Override
    public City addCity(CityRequestDto cityRequestDto) {

        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    // Search city by id (Buscar cidade por id)
    @Override
    public City getCity(Long cityId) {

        return cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("city with cityId: " + cityId + " could not be found"));
    }

    // List all cities (Listar todas as cidades)
    @Override
    public List<City> getCities() {

        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    // Delete city by id (Deletar cidade por id)
    @Override
    public City deleteCity(Long cityId) {
        
        City city = getCity(cityId);
        cityRepository.delete(city);
        return city;

    }
    
    // Edit city by id (Editar cidade por id)
    @Transactional
    @Override
    public City editCity(Long cityId, CityRequestDto cityRequestDto) {
        
        City cityToEdit = getCity(cityId);
        cityToEdit.setName(cityRequestDto.getName());
        return cityToEdit;
    }

}
