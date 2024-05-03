package com.exam.example.examportalproject.repository;

import com.exam.example.examportalproject.model.category.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
