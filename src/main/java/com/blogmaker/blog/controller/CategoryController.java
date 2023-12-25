package com.blogmaker.blog.controller;


import com.blogmaker.blog.dtos.CategoryDTO;
import com.blogmaker.blog.entity.Category;
import com.blogmaker.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/getAll")
    public List<Category> getAll(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/{Id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long Id){
       return categoryService.updateCategory(categoryDTO,Id);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long Id){
        return categoryService.deleteCategory(Id);
    }



}
