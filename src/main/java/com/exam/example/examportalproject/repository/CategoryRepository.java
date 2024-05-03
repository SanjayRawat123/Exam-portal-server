package com.exam.example.examportalproject.repository;

import com.exam.example.examportalproject.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
