package com.datajpa.relationship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.AuthorRequestDto;
import com.datajpa.relationship.dto.responseDto.AuthorResponseDto;
import com.datajpa.relationship.model.Author;

@Service
public interface AuthorService {

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    public List<AuthorResponseDto> getAuthors();

    public AuthorResponseDto getAuthorById(Long authorId);

    public Author getAuthor(Long authorId);

    public AuthorResponseDto deleteAuthor(Long authorId);

    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);

    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId);

    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId);
    
}
