package com.datajpa.relationship.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datajpa.relationship.dto.requestDto.ZipcodeRequestDto;
import com.datajpa.relationship.model.Zipcode;
import com.datajpa.relationship.service.ZipcodeService;

@RestController
@RequestMapping("/api/zipcode")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    // localhost:8080/api/zipcode/add
    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto) {
        
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable final Long id) {
        
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Zipcode>> getZipcodes() {
        
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/edit/{id}
    @PostMapping("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto, @PathVariable final Long id) {
        
        Zipcode zipcode = zipcodeService.editZipcode(id, zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/addCity/{cityId}/toZipcode/{zipcodeId}
    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addCity(@PathVariable final Long cityId, @PathVariable final Long zipcodeId) {
        
        Zipcode zipcode = zipcodeService.addCityToZipcode(zipcodeId, cityId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    // localhost:8080/api/zipcode/deleteCity/{zipcodeId}
    @PostMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<Zipcode> deleteCity(@PathVariable final Long zipcodeId) {
        
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

}
