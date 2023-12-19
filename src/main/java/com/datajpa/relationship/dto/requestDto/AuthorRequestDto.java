package com.datajpa.relationship.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthorRequestDto {

    private String name;

    @JsonProperty("zipcode_id")
    private Long zipcodeId;
    
}
