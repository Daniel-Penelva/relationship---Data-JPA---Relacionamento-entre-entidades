package com.datajpa.relationship.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.requestDto.AuthorRequestDto;
import com.datajpa.relationship.dto.responseDto.AuthorResponseDto;
import com.datajpa.relationship.model.Author;
import com.datajpa.relationship.model.Zipcode;
import com.datajpa.relationship.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    // create Author (criar ator)
    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {

        Author author = new Author();
        author.setName(authorRequestDto.getName());
        
        if (authorRequestDto.getZipcodeId() == null) {
            throw new IllegalArgumentException("author nedd a zipcode");
        }

        Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        
        return mapper.authorToAuthorResponseDto(author);

    }


    // List all authors (Listando todos os autores)
    @Override
    public List<AuthorResponseDto> getAuthors() {

        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        
        return mapper.authorsToAuthorResponseDtos(authors);
    }


    // Search author by id (Buscar autor por id)
    @Override
    public AuthorResponseDto getAuthorById(Long authorId) { 

        return mapper.authorToAuthorResponseDto(getAuthor(authorId)); // chama o método getAuthor com o id do autor e lança para o método authorToAuthorResponseDto com o id dele.
    }


    // Search author (Buscar autor)
    @Override
    public Author getAuthor(Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("author with id: " + authorId + " could not be found"));

        return author;
    }


    // Delete author by id (Deletar autor por id)
    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    // Edit author by id (Editar autor por id)
    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {

        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());

        if (authorRequestDto.getZipcodeId() != null) {
            Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            authorToEdit.setZipcode(zipcode);
        }

        return mapper.authorToAuthorResponseDto(authorToEdit);
    }

    // Add zip code to author (adicionar CEP ao autor)
    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {

        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);

        if (author.getZipcode() == null) {
            throw new RuntimeException("author already has a zipcode");
        }

        author.setZipcode(zipcode);
        return mapper.authorToAuthorResponseDto(author);
    }

    // Delete author zip code (Excluir CEP do autor)
    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {

        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return mapper.authorToAuthorResponseDto(author);
    }

}
