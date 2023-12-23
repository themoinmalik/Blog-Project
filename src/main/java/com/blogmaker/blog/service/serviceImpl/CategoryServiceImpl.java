package com.blogmaker.blog.service.serviceImpl;


import com.blogmaker.blog.dtos.CategoryDTO;
import com.blogmaker.blog.entity.Category;
import com.blogmaker.blog.exception.ResourceNotFoundException;
import com.blogmaker.blog.repository.CategoryRepo;
import com.blogmaker.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepo.save(category);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long Id) {
        Category category = categoryRepo.findById(Id).orElseThrow( () -> new ResourceNotFoundException("There is no Category for this Id"));
        categoryDTO.setTitle(category.getTitle());
        category.setDescription(category.getDescription());
        category.setId(Id);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No category found"));
        categoryRepo.deleteById(category.getId());
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDTO> findById(Long catId) {
        Optional<Category> category = categoryRepo.findById(catId);
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
}