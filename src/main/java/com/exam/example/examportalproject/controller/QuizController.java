/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.exception.QuizNotFoundException;
import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/quiz")
@CrossOrigin("*")
public class QuizController {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;


    /**
     * Add a new quiz.
     *
     * @param quiz the quiz to add
     * @return the added quiz wrapped in a standardized response
     */
    @PostMapping(value = "/")
    public ResponseEntity<ApiResponse<Quiz>> addQuiz(@RequestBody Quiz quiz) {
        logger.info("Adding new quiz: {}", quiz.getTitle());
        try {
            Quiz addQuiz = quizService.addQuiz(quiz);
            ApiResponse<Quiz> response = new ApiResponse<>("success", "Quiz Added successfully", addQuiz);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.info("Error adding quiz: {}", e.getMessage());
            ApiResponse<Quiz> apiResponse = new ApiResponse<>("error", "Error adding quiz", null);
            return ResponseEntity.status(500).body(apiResponse);
        }
    }

    /**
     * Update an existing quiz.
     *
     * @param quiz the quiz to update
     * @return the updated quiz wrapped in a standardized response
     */
    @PutMapping(value = "/")
    public ResponseEntity<ApiResponse<Quiz>> update(@RequestBody Quiz quiz) {
        logger.info("Updating quiz with ID: {}", quiz.getqId());
        try {
            Quiz updatedQuiz = quizService.updateQuiz(quiz.getqId(), quiz);
            ApiResponse<Quiz> response = new ApiResponse<>("success", "Quiz updated successfully", updatedQuiz);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating quiz: {}", e.getMessage());
            ApiResponse<Quiz> response = new ApiResponse<>("error", "Error updating quiz", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Get quizzes list.
     *
     * @return list of quizzes wrapped in a standardized response
     */
    @GetMapping(value = "/")
    public ResponseEntity<ApiResponse<List<Quiz>>> getQuizzes() {
        logger.info("Fetching all quizzes");
        try {
            List<Quiz> quizzes = quizService.getQuizzes();
            ApiResponse<List<Quiz>> response = new ApiResponse<>("success", "Quizzes retrieved succesfully", quizzes);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating quiz: {}", e.getMessage());
            ApiResponse<List<Quiz>> response = new ApiResponse<>("error", "Error Fetching quizzes", null);
            return ResponseEntity.status(5000).body(response);
        }

    }

    /**
     * Get a quiz by ID.
     *
     * @param qId the quiz ID
     * @return the quiz wrapped in a standardized response
     */
    @GetMapping(value = "/{qId}")
    public ResponseEntity<ApiResponse<Quiz>> getQuiz(@PathVariable("qId") long qId) {

        logger.info("Fetching quiz with ID: {}", qId);

        try {
            Quiz quiz = quizService.getQuizById(qId);
            ApiResponse<Quiz> response = new ApiResponse<>("success", "Quiz retrieved successfully", quiz);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching quiz with ID {}: {}", qId, e.getMessage());
            ApiResponse<Quiz> response = new ApiResponse<>("error", "Error fetching quiz", null);
            return ResponseEntity.status(500).body(response);
        }

    }


    /*    * Delete a quiz by ID.
     *
     * @param cId the quiz ID
     * @return a standardized response indicating the result of the deletion
     */
    @DeleteMapping(value = "/{qId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuiz(@PathVariable Long qId) {
        logger.info("Deleting quiz with ID: {}", qId);
        try {
            quizService.deleteQuiz(qId);
            ApiResponse<Void> response = new ApiResponse<>("success", "Quiz deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (QuizNotFoundException e) {
            logger.error("Quiz not found with ID {}: {}", qId, e.getMessage());
            ApiResponse<Void> response = new ApiResponse<>("error", e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("Error deleting quiz with ID {}: {}", qId, e.getMessage());
            ApiResponse<Void> response = new ApiResponse<>("error", "Error deleting quiz", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
