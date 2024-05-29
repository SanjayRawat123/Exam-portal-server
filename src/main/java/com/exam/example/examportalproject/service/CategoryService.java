package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param category the category to be added
     * @return the saved category
     */
    public Category addCategory(Category category);


    /**
     * Updates an existing category.
     *
     * @param category the category to be updated
     * @return the updated category
     */
    public Category updateCategory(Long categoryId ,Category category);


    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    List<Category> getCategories();

    /**
     * Retrieves a category by its ID.
     *
     * @param cId the ID of the category to retrieve
     * @return the category with the specified ID
     */
    public Category getCategoryById(Long cId);

    /**
     * Deletes a category by its ID.
     *
     * @param cId the ID of the category to delete
     */
    public void deleteCategory(Long cId);
}
