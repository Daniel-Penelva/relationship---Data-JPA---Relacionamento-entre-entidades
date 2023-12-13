package com.datajpa.relationship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.ZipcodeRequestDto;
import com.datajpa.relationship.model.Zipcode;

@Service
public interface ZipcodeService {

    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);

    public List<Zipcode> getZipcodes();

    public Zipcode getZipcode(Long zipcodeId);

    public Zipcode deleteZipcode(Long zipcodeId);

    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);

    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId);

    public Zipcode removeCityFromZipcode(Long zipcodeId);
    
}
