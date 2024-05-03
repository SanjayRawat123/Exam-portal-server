package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Question;
import com.exam.example.examportalproject.model.category.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question>getQuestion();

    public Question getQuestionBy(Long quesId);

    public Set<Question>getQuestionOfQuiz(Quiz quiz);

    public void deleteQustion(Long quesId);
}
