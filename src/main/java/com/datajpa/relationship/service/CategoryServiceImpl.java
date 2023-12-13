package com.datajpa.relationship.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.requestDto.CategoryRequestDto;
import com.datajpa.relationship.dto.responseDto.CategoryResponseDto;
import com.datajpa.relationship.model.Category;
import com.datajpa.relationship.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // (Criar categoria)
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        categoryRepository.save(category);
        
        return mapper.categoryToCategoryResponseDto(category);
    }


    // (Buscar categoria por id)
    @Override
    public Category getCategory(Long categoryId) {
        
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("could not find category with id: " + categoryId));
    }


    // (Buscar categoria por id)
    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        
        Category category = getCategory(categoryId);
        return mapper.categoryToCategoryResponseDto(category);
    }


    // (Listar categorias por id)
    @Override
    public List<CategoryResponseDto> getCategories() {
        
        List<Category> categories = StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        
                return mapper.categoriesToCategoryResponseDtos(categories);
    }


    // (Deletar categoria por id)
    @Override
    public CategoryResponseDto deleteCategory(Long categoryId) {
        
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        
        return mapper.categoryToCategoryResponseDto(category);
    }


    // (Editar categoria por id)
    @Transactional
    @Override
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        
        Category categoryToEdit = getCategory(categoryId);
        categoryToEdit.setName(categoryRequestDto.getName());
        
        return mapper.categoryToCategoryResponseDto(categoryToEdit);
    }

    

}
