package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Question;
import com.exam.example.examportalproject.model.category.Quiz;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);

    Question updateQuestion(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(Long questionId);

    List<Question> getQuestionsOfQuiz(Quiz quiz);

    void deleteQuestion(Long questionId);


}
