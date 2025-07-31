package org.example.controller;

import org.example.domain.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(201).body(savedCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId, @RequestBody @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
