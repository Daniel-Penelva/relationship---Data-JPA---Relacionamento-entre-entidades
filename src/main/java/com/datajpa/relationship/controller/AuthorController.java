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

import com.datajpa.relationship.dto.requestDto.AuthorRequestDto;
import com.datajpa.relationship.dto.responseDto.AuthorResponseDto;
import com.datajpa.relationship.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // localhost:8080/api/author/addAuthor
    @PostMapping("/addAuthor")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody final AuthorRequestDto authorRequestDto){

        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/author/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable final Long id){
        
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/author/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(){

        List<AuthorResponseDto> authorResponseDto = authorService.getAuthors();
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/author/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable final Long id){

        AuthorResponseDto authorResponseDto = authorService.deleteAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/author/edit/{id}
    @PostMapping("/edit/{id}")
    private ResponseEntity<AuthorResponseDto> editAuthor(@PathVariable final Long id,  @RequestBody final AuthorRequestDto authorRequestDto) {
        
        AuthorResponseDto authorResponseDto = authorService.editAuthor(id, authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/author/addZipcode/{zipcodeId}/to/{authorId}
    @PostMapping("/addZipcode/{zipcodeId}/to/{authorId}")
    private ResponseEntity<AuthorResponseDto> addZipcode(@PathVariable final Long zipcodeId, @PathVariable final Long authorId) {
        
        AuthorResponseDto authorResponseDto = authorService.addZipcodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }


    // localhost:8080/api/author/removeZipcode/{id}
    @PostMapping("/removeZipcode/{id}")
    private ResponseEntity<AuthorResponseDto> removeZipcode(@PathVariable final Long id) {
        
        AuthorResponseDto authorResponseDto = authorService.deleteZipcodeFromAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

}
