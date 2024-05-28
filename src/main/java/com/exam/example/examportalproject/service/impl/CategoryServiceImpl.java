/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

import com.exam.example.examportalproject.exception.CategoryNotFoundException;
import com.exam.example.examportalproject.model.category.Category;
import com.exam.example.examportalproject.repository.CategoryRepository;
import com.exam.example.examportalproject.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);


    @Autowired
    private CategoryRepository categoryRepository;

      public Category addCategory(Category category) {
        if (category == null) {
            logger.error("Attempted to add a null category");
            throw new IllegalArgumentException("Category cannot be null");
        }

        try {
            Category savedCategory = categoryRepository.save(category);
            logger.info("Category added successfully: {}", savedCategory);
            return savedCategory;
        } catch (Exception e) {
            logger.error("Error while adding category: {}", e.getMessage());
            throw new RuntimeException("Could not save category", e);
        }
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }

        Category existingCategory = optionalCategory.get();
        updateCategoryFields(existingCategory, category);
        return categoryRepository.save(existingCategory);
    }

    // Helper method to update fields of existing category
    private void updateCategoryFields(Category existingCategory, Category updatedCategory) {
        if (updatedCategory.getTitle() != null) {
            existingCategory.setTitle(updatedCategory.getTitle());
        }
        if (updatedCategory.getDescription() != null) {
            existingCategory.setDescription(updatedCategory.getDescription());
        }
        // Add more fields to update as needed
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategoryById(Long cId) {
        return this.categoryRepository.findById(cId).get();
    }

    @Override
    public void deleteCategory(Long cId) {
       Category category =new Category();
       category.setcId(cId);
       this.categoryRepository.delete( category);
    }
}
