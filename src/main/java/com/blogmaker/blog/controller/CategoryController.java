package com.blogmaker.blog.controller;


import com.blogmaker.blog.dtos.CategoryDTO;
import com.blogmaker.blog.entity.Category;
import com.blogmaker.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/update")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, Long Id){
       return categoryService.updateCategory(categoryDTO,Id);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }



}
