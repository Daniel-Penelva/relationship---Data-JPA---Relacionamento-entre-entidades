package com.datajpa.relationship.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datajpa.relationship.dto.requestDto.CityRequestDto;
import com.datajpa.relationship.model.City;
import com.datajpa.relationship.service.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // localhost:8080/api/city/add
    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody final CityRequestDto cityRequestDto) {
        City city = cityService.addCity(cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    // localhost:8080/api/city/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<City> getCityById(@PathVariable final Long id) {
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    // localhost:8080/api/city/getall
    @GetMapping("/getall")
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.getCities();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }

    // localhost:8080/api/city/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable final Long id) {
        City city = cityService.deleteCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    // localhost:8080/api/city/edit/id
    @PostMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@RequestBody final CityRequestDto cityRequestDto, @PathVariable final Long id) {
        City city = cityService.editCity(id, cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
