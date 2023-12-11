package com.datajpa.relationship.dto.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponseDto {

    private Long id;
    private String name;
    private List<String> bookNames;
    
}
