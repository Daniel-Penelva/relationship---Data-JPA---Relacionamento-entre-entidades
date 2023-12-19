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

import com.datajpa.relationship.dto.requestDto.CategoryRequestDto;
import com.datajpa.relationship.dto.responseDto.CategoryResponseDto;
import com.datajpa.relationship.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // localhost:8080/api/category/add
    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody final CategoryRequestDto categoryRequestDto) {
        
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/category/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable final Long id) {
        
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/category/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return new ResponseEntity<>(categoryResponseDtos, HttpStatus.OK);
    }

    // localhost:8080/api/category/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable final Long id) {
        
        CategoryResponseDto categoryResponseDto = categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    // localhost:8080/api/category/edit/{id}
    @PostMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> editCategory(@RequestBody final CategoryRequestDto categoryRequestDto, @PathVariable final Long id) {
        
        CategoryResponseDto categoryResponseDto = categoryService.editCategory(id, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

}
