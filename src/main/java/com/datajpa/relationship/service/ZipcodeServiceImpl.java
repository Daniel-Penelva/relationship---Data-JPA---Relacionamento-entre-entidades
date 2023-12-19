package com.datajpa.relationship.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.ZipcodeRequestDto;
import com.datajpa.relationship.model.City;
import com.datajpa.relationship.model.Zipcode;
import com.datajpa.relationship.repository.ZipcodeRepository;

import jakarta.transaction.Transactional;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ZipcodeRepository zipcodeRepository;
    private final CityService cityService;

    public ZipcodeServiceImpl(ZipcodeRepository zipcodeRepository, CityService cityService) {
        this.zipcodeRepository = zipcodeRepository;
        this.cityService = cityService;
    }

    // Create zip code (Criar CEP)
    @Transactional
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {

        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());

        if (zipcodeRequestDto.getCityId() == null) {
            return zipcodeRepository.save(zipcode);
        }

        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }


    // List all zip codes (Listar todos os CEP)
    public List<Zipcode> getZipcodes() {

        return StreamSupport.stream(zipcodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    // Search zip code by id (Buscar CEP por id)
    public Zipcode getZipcode(Long zipcodeId) {

        return zipcodeRepository.findById(zipcodeId).orElseThrow(
                () -> new IllegalArgumentException("zipcode with id: " + zipcodeId + " could not be found"));
    }


    // Delete zip code by id (Deletar CEP por id)
    public Zipcode deleteZipcode(Long zipcodeId) {

        Zipcode zipcode = getZipcode(zipcodeId);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }


    // Edit zip code by id (Editar CEP por id)
    @Transactional
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto) {

        Zipcode zipcodeToEdit = getZipcode(zipcodeId);
        zipcodeToEdit.setName(zipcodeRequestDto.getName());

        if (zipcodeRequestDto.getCityId() == null) { // verifica se o id da cidade está nulo
            return zipcodeToEdit;
        }

        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcodeToEdit.setCity(city);
        return zipcodeToEdit;
    }

    // Add city to zip code (Adicionar cidade ao CEP)
    @Transactional
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {

        Zipcode zipcode = getZipcode(zipcodeId); // captura o id do cep
        City city = cityService.getCity(cityId); // captura o id da cidade

        if (Objects.nonNull(zipcode.getCity())) {
            throw new IllegalArgumentException("zipcode already has a city");
        }

        zipcode.setCity(city);
        return zipcode;

    }

    // Remove city from zip code (Remover cidade do CEP)
    @Transactional
    public Zipcode removeCityFromZipcode(Long zipcodeId) {

        Zipcode zipcode = getZipcode(zipcodeId);

        if (zipcode.getCity() == null) {
            throw new IllegalArgumentException("zipcode does not have a city");
        }

        zipcode.setCity(null);
        return zipcode;
    }

}
