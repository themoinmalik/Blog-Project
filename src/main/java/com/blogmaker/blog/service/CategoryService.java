package com.blogmaker.blog.service;

import com.blogmaker.blog.dtos.CategoryDTO;
import com.blogmaker.blog.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    // create
    ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO);

    // update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long Id);

    // get
    CategoryDTO getCategory(Long id);


    // get all
    List<Category> getAllCategories();


    // delete...
    ResponseEntity<Category> deleteCategory(Long id);


    ResponseEntity<CategoryDTO> findById(Long catId);
}
