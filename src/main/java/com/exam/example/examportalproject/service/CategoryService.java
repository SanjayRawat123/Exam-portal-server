package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Category;

import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Set<Category> getCategories();

    public Category getCategoryById(Long cId);

    public void deleteCategory(Long cId);
}
