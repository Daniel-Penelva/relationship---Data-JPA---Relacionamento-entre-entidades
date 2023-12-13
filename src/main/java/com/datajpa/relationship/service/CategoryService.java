package com.datajpa.relationship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.requestDto.CategoryRequestDto;
import com.datajpa.relationship.dto.responseDto.CategoryResponseDto;
import com.datajpa.relationship.model.Category;

@Service
public interface CategoryService {

    public Category getCategory(Long categoryId);

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    public CategoryResponseDto getCategoryById(Long categoryId);

    public List<CategoryResponseDto> getCategories();

    public CategoryResponseDto deleteCategory(Long categoryId);

    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);

}
