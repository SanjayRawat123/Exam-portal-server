/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

import com.exam.example.examportalproject.exception.CategoryNotFoundException;
import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.repository.QuizRepository;
import com.exam.example.examportalproject.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        if (quiz == null) {
            logger.error("Attempted to add a null quiz");
            throw new IllegalArgumentException("quiz cannot be null");
        }
        try {
            Quiz savedQuiz = quizRepository.save(quiz);
            ;
            logger.info("Quiz added successfully: {}", savedQuiz);
            return savedQuiz;
        } catch (Exception e) {
            logger.error("Error while adding quiz: {}", e.getMessage());
            throw new RuntimeException("Could not save quiz", e);
        }

    }

    @Override
    public Quiz updateQuiz(Long qId, Quiz quiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(qId);
        if (optionalQuiz.isEmpty()) {
            throw new CategoryNotFoundException("quiz with ID " + qId + " not found");
        }

        Quiz existingQuiz = optionalQuiz.get();
        updateQuizFields(existingQuiz, quiz);
        return quizRepository.save(existingQuiz);
    }

    private void updateQuizFields(Quiz existingQuiz, Quiz updatedQuiz) {
        if (updatedQuiz.getTitle() != null) {
            existingQuiz.setTitle(updatedQuiz.getTitle());
        }
        if (updatedQuiz.getDescription() != null) {
            existingQuiz.setDescription(updatedQuiz.getDescription());
        }
        if (updatedQuiz.getCategory() != null) {
            existingQuiz.setCategory(updatedQuiz.getCategory());
        }
        if (updatedQuiz.getMaxMarks() != null) {
            existingQuiz.setMaxMarks(updatedQuiz.getMaxMarks());
        }
        if (updatedQuiz.isActive()) {
            existingQuiz.setActive(updatedQuiz.isActive());
        }
    }

    @Override
    public List<Quiz> getQuizzes() {
        try {
            List<Quiz> quizzes = quizRepository.findAll();
            logger.info("Retrieved {} quizzes", quizzes.size());
            return quizzes;
        } catch (Exception e) {
            logger.error("Error while retrieving quizzes: {}", e.getMessage());
            throw new RuntimeException("Could not retrieve quizzes", e);
        }
    }

    @Override
    public Quiz getQuizById(Long qId) {
        return quizRepository.findById(qId)
                .orElseThrow(() -> new CategoryNotFoundException("quiz with ID " + qId + " not found"));
    }

    @Override
    public void deleteQuiz(Long qId) {
        if (!quizRepository.existsById(qId)) {
            throw new CategoryNotFoundException("Category with ID " + qId + " not found");
        }
        try {
            quizRepository.deleteById(qId);
            logger.info("quiz with ID {} deleted successfully", qId);
        } catch (Exception e) {
            logger.error("Error while deleting quiz with ID {}: {}", qId, e.getMessage());
            throw new RuntimeException("Could not delete quiz", e);
        }

    }


}
