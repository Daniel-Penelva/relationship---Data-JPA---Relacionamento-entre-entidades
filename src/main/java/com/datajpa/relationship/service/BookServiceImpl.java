package com.datajpa.relationship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.requestDto.BookRequestDto;
import com.datajpa.relationship.dto.responseDto.BookResponseDto;
import com.datajpa.relationship.model.Author;
import com.datajpa.relationship.model.Book;
import com.datajpa.relationship.model.Category;
import com.datajpa.relationship.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    // (Criar livro)
    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if (bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("you need atleast on author");

        } else {
            
            List<Author> authors = new ArrayList();
            
            for (Long authorId: bookRequestDto.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            
            book.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("book atleast on category");
        }
        
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book book1 = bookRepository.save(book);
        return mapper.bookToBookResponseDto(book1);
    }


    // (buscar livro por id)
    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);
        return mapper.bookToBookResponseDto(book);
    }


    // (buscar livro por id)
    @Override
    public Book getBook(Long bookId) {
        
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("cannot find book with id: " + bookId));
        
                return book;
    }


    // (listar todos os livros)
    @Override
    public List<BookResponseDto> getBooks() {
       
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        
                return mapper.booksToBookResponseDtos(books);
    }


    // (deletar livro por id)
    @Override
    public BookResponseDto deleteBook(Long bookId) {
        
        Book book = getBook(bookId);
        bookRepository.delete(book);
        
        return mapper.bookToBookResponseDto(book);
    }


    // (editar livro por id)
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        
        if (!bookRequestDto.getAuthorIds().isEmpty()){

            List<Author> authors = new ArrayList<>();

            for (Long authorId: bookRequestDto.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }

            bookToEdit.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId() != null) {
            
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return mapper.bookToBookResponseDto(bookToEdit);
    }

    
    // (adicionar autor ao livro)
    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        
        if (author.getBooks().contains(author)) {
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        
        book.addAuthor(author);
        author.addBook(book);
        
        return mapper.bookToBookResponseDto(book);
    }


    // (excluir autor do livro)
    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        
        if (!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("book does not have this author");
        }
        
        author.deleteBook(book);
        book.deleteAuthor(author);
        return mapper.bookToBookResponseDto(book);
    }

    
    // (adicionar categoria ao livro)
    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        
        if (Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book already has a catogory");
        }
       
        book.setCategory(category);
        category.addBook(book);
        
        return mapper.bookToBookResponseDto(book);
    }
    

    // (remover categoria do livro)
    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        
        if (!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not have a category to delete");
        }
        
        book.setCategory(null);
        category.deleteBook(book);
        
        return mapper.bookToBookResponseDto(book);
    }
    
}
