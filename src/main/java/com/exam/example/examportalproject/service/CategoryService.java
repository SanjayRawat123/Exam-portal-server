package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Category;

import java.util.Set;

public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param category the category to be added
     * @return the saved category
     */
    public Category addCategory(Category category);


    public Category updateCategory(Long categoryId ,Category category);

    public Set<Category> getCategories();

    public Category getCategoryById(Long cId);

    public void deleteCategory(Long cId);
}
