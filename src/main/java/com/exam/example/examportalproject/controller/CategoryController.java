/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.category.Category;
import com.exam.example.examportalproject.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin("*")
@Validated
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * Add a new category.
     *
     * @param category the category to add
     * @return the added category wrapped in a standardized response
     */
    @PostMapping(value = "/")
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody Category category) {
        logger.info("Adding new category: {}", category.getTitle());

        try {
            Category addedCategory = categoryService.addCategory(category);
            ApiResponse<Category> response = new ApiResponse<>("success", "Category added successfully", addedCategory);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
                logger.error("Error adding category: {}", e.getMessage());
            ApiResponse<Category> response = new ApiResponse<>("error", "Error adding category", null);
            return ResponseEntity.status(500).body(response);
        }
    }


    /**
     * Get all categories.
     *
     * @return the list of categories wrapped in a standardized response
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getCategories() {
        logger.info("Fetching all categories");

        try {
            List<Category> categories = categoryService.getCategories();
            ApiResponse<List<Category>> response = new ApiResponse<>("success", "Categories retrieved successfully", categories);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching categories: {}", e.getMessage());
            ApiResponse<List<Category>> response = new ApiResponse<>("error", "Error fetching categories", null);
            return ResponseEntity.status(500).body(response);
        }
    }


    /**
     * Get a category by ID.
     *
     * @param cId the category ID
     * @return the category wrapped in a standardized response
     */
    @GetMapping("/{cId}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable long cId) {
        logger.info("Fetching category with ID: {}", cId);

        try {
            Category category = categoryService.getCategoryById(cId);
            ApiResponse<Category> response = new ApiResponse<>("success", "Category retrieved successfully", category);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching category with ID {}: {}", cId, e.getMessage());
            ApiResponse<Category> response = new ApiResponse<>("error", "Error fetching category", null);
            return ResponseEntity.status(500).body(response);
        }
    }


    /**
     * Update an existing category.
     *
     * @param category the category to update
     * @return the updated category wrapped in a standardized response
     */
    @PutMapping(value = "/")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@RequestBody Category category) {
        logger.info("Updating category with ID: {}", category.getcId());

        try {
            Category updatedCategory = categoryService.updateCategory(category.getcId() ,category);
            ApiResponse<Category> response = new ApiResponse<>("success", "Category updated successfully", updatedCategory);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating category: {}", e.getMessage());
            ApiResponse<Category> response = new ApiResponse<>("error", "Error updating category", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    //delete category

    @DeleteMapping(value = "/{cId}")
    public void deleteCatrgory(@PathVariable ("cId") long cId){
        this.categoryService.deleteCategory(cId);
    }

}
