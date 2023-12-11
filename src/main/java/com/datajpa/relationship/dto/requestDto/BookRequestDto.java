package com.datajpa.relationship.dto.requestDto;

import java.util.List;

import lombok.Data;

@Data
public class BookRequestDto {

    private String name;
    private List<Long> authorIds;
    private Long categoryId;


    
}
