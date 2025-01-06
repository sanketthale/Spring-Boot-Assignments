package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Category> addCategory(Category category){

        Category createdCategory = this.categoryService.addCategory(category);

        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/premium")
    public ResponseEntity<List<Category>> getPremiumCategories(){

        List<Category> categories = this.categoryService.getAllPremiumCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId1}/{categoryId2}")
    public ResponseEntity<List<Category>> getCategoriesBetweenIds(
            @PathVariable("categoryId1") Long categoryId1,
            @PathVariable("categoryId2") Long categoryId2
    ){
        List<Category> categories = this.categoryService.getCategoriesBetweenIds(categoryId1, categoryId2);

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Category>> getCategoriesWithName(@PathVariable("categoryName") String categoryName){

        List<Category> categories = this.categoryService.getCategoriesWithMatchingName(categoryName);

        return ResponseEntity.ok(categories);
    }


}
