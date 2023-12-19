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

import com.datajpa.relationship.dto.requestDto.BookRequestDto;
import com.datajpa.relationship.dto.responseDto.BookResponseDto;
import com.datajpa.relationship.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // localhost:8080/api/book/add
    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody final BookRequestDto bookRequestDto) {
        
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/book/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable final Long id) {
        
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/book/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        
        List<BookResponseDto> bookResponseDtos = bookService.getBooks();
        return new ResponseEntity<>(bookResponseDtos, HttpStatus.OK);
    }

    // localhost:8080/api/book/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable final Long id) {
        
        BookResponseDto bookResponseDto = bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/book/edit/{id}
    @PostMapping("/edit/{id}")
    public ResponseEntity<BookResponseDto> editBook(@RequestBody final BookRequestDto bookRequestDto, @PathVariable final Long id) {
        
        BookResponseDto bookResponseDto = bookService.editBook(id, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/book/addCategory/{categoryId}/to/{bookId}
    @PostMapping("/addCategory/{categoryId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addCategory(@PathVariable final Long categoryId, @PathVariable final Long bookId) {
        
        BookResponseDto bookResponseDto = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

     // localhost:8080/api/book/removeCategory/{categoryId}/from/{bookId}
    @PostMapping("/removeCategory/{categoryId}/from/{bookId}")
    public ResponseEntity<BookResponseDto> removeCategory(@PathVariable final Long categoryId, @PathVariable final Long bookId) {
        
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

     // localhost:8080/api/book/addAuthor/{authorId}/to/{bookId}
    @PostMapping("/addAuthor/{authorId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addAuthor(@PathVariable final Long authorId, @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

     // localhost:8080/api/book/removeAuthor/{authorId}/from/{bookId}
    @PostMapping("/removeAuthor/{authorId}/from/{bookId}")
    public ResponseEntity<BookResponseDto> removeAuthor(@PathVariable final Long authorId,
                                                        @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.deleteAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    
}
