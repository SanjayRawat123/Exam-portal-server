package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Quiz;

import javax.persistence.SecondaryTable;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuizById(Long qId);

    public void deleteQuiz(Long qId);



}
