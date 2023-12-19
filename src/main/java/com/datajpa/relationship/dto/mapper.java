package com.datajpa.relationship.dto;

import java.util.ArrayList;
import java.util.List;

import com.datajpa.relationship.dto.responseDto.AuthorResponseDto;
import com.datajpa.relationship.dto.responseDto.BookResponseDto;
import com.datajpa.relationship.dto.responseDto.CategoryResponseDto;
import com.datajpa.relationship.model.Author;
import com.datajpa.relationship.model.Book;
import com.datajpa.relationship.model.Category;

public class mapper {

    /** Este método converte um objeto da classe Book para um objeto da classe BookResponseDto. Ele extrai informações específicas do livro, 
     * como o ID, o nome da categoria e os nomes dos autores, e as coloca em um objeto BookResponseDto.*/
    public static BookResponseDto bookToBookResponseDto(Book book) {

        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategoryName(book.getCategory().getName());

        List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();

        for (Author author : authors) {
            names.add(author.getName());
        }

        bookResponseDto.setAuthorNames(names);
        return bookResponseDto;

    }

    /**Este método converte uma lista de objetos da classe Book para uma lista de objetos da classe BookResponseDto. Ele utiliza o método 
     * bookToBookResponseDto para realizar a conversão individual de cada livro na lista.*/
    public static List<BookResponseDto> booksToBookResponseDtos(List<Book> books) {

        List<BookResponseDto> bookResponseDtos = new ArrayList<>();

        for (Book book : books) {
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    /**Este método é similar ao primeiro método, este converte um objeto da classe Author para um objeto da classe AuthorResponseDto. Ele extrai 
     * informações como o ID, o nome do autor e os nomes dos livros associados ao autor*/
    public static AuthorResponseDto authorToAuthorResponseDto(Author author) {

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());

        List<String> names = new ArrayList<>();
        List<Book> books = author.getBooks();

        for (Book book : books) {
            names.add(book.getName());
        }

        authorResponseDto.setBookNames(names);
        return authorResponseDto;
    }

    /**Este método converte uma lista de objetos da classe Author para uma lista de objetos da classe AuthorResponseDto. Ele utiliza o método 
     * authorToAuthorResponseDto para realizar a conversão individual de cada autor na lista.*/
    public static List<AuthorResponseDto> authorsToAuthorResponseDtos(List<Author> authors){

         List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();

         for (Author author : authors) {
            authorResponseDtos.add(authorToAuthorResponseDto(author));
         }

         return authorResponseDtos;
    }

    /**Esse método é semelhante aos métodos anteriores, este converte um objeto da classe Category para um objeto da classe CategoryResponseDto.
     * Ele extrai informações como o ID, o nome da categoria e os nomes dos livros associados à categoria.*/
    public static CategoryResponseDto categoryToCategoryResponseDto(Category category){
        
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());

        List<String> names = new ArrayList();
        List<Book> books = category.getBooks();

        for (Book book : books) {
            names.add(book.getName());
        }

        categoryResponseDto.setBookNames(names);
        return categoryResponseDto;

    }

    /**Este método converte uma lista de objetos da classe Category para uma lista de objetos da classe CategoryResponseDto. Ele utiliza o método 
     * categoryToCategoryResponseDto para realizar a conversão individual de cada categoria na lista.*/
    public static List<CategoryResponseDto> categoriesToCategoryResponseDtos(List<Category> categories){

        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();

        for(Category category: categories){
            categoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }

        return categoryResponseDtos;
    }

}
