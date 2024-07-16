package com.exam.example.examportalproject.repository;

import com.exam.example.examportalproject.model.category.Category;
import com.exam.example.examportalproject.model.category.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
   public List<Quiz> findBycategory(Category category);
}
