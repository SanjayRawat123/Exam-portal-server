/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

import com.exam.example.examportalproject.model.category.Question;
import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.repository.QuestionRepository;
import com.exam.example.examportalproject.repository.QuizRepository;
import com.exam.example.examportalproject.service.QuestionService;
import com.exam.example.examportalproject.service.QuizService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public List<Question> getQuestionsOfQuiz(Quiz quiz) {
        Quiz fullQuiz = quizService.getQuizById(quiz.getqId());
        Hibernate.initialize(fullQuiz.getQuestions());
        return new ArrayList<>(fullQuiz.getQuestions());
    }
    @Override
    public List<Question> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            logger.info("Retrieved {} questions", questions.size());
            return questions;
        } catch (Exception e) {
            logger.error("Error while retrieving questions: {}", e.getMessage());
            throw new RuntimeException("Could not retrieve questions", e);
        }
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question with ID " + questionId + " not found"));
    }

    @Override
    public void deleteQuestion(Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            logger.info("Question with ID {} deleted successfully", questionId);
        } else {
            throw new RuntimeException("Question with ID " + questionId + " does not exist");
        }
    }
}
