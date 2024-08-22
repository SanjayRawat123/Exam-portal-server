/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */

package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.category.Question;
import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.service.QuestionService;
import com.exam.example.examportalproject.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/question")
@Validated
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    /**
     * Add a new question.
     *
     * @param question the question to add
     * @return the added question wrapped in a standardized response
     */
    @PostMapping(value = "/")
    public ResponseEntity<ApiResponse<Question>> addQuestion(@RequestBody Question question) {
        logger.info("Adding new question: {}", question.getContent());

        try {
            Question addedQuestion = questionService.addQuestion(question);
            ApiResponse<Question> response = new ApiResponse<>("success", "Question added successfully", addedQuestion);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error adding question: {}", e.getMessage());
            ApiResponse<Question> response = new ApiResponse<>("error", "Error adding question", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Update an existing question.
     *
     * @param question the question to update
     * @return the updated question wrapped in a standardized response
     */
    @PutMapping(value = "/")
    public ResponseEntity<ApiResponse<Question>> updateQuestion(@RequestBody Question question) {
        logger.info("Updating question with ID: {}", question.getQuesId());

        try {
            Question updatedQuestion = questionService.updateQuestion(question);
            ApiResponse<Question> response = new ApiResponse<>("success", "Question updated successfully", updatedQuestion);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating question: {}", e.getMessage());
            ApiResponse<Question> response = new ApiResponse<>("error", "Error updating question", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Get all questions of a quiz.
     *
     * @param quizId the quiz ID
     * @return the list of questions wrapped in a standardized response
     */
    @GetMapping(value = "/quiz/{quizId}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        logger.info("Fetching questions for quiz with ID: {}", quizId);

        try {
            Quiz quiz = quizService.getQuizById(quizId);
            List<Question> questions = new ArrayList<>(quiz.getQuestions());
            int numOfQuestions = Integer.parseInt(quiz.getNumOfQuestions());

            if (questions.size() > numOfQuestions) {
                questions = questions.subList(0, numOfQuestions);
            }

            Collections.shuffle(questions);
            ApiResponse<List<Question>> response = new ApiResponse<>("success", "Questions retrieved successfully", questions);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching questions for quiz with ID {}: {}", quizId, e.getMessage());
            ApiResponse<List<Question>> response = new ApiResponse<>("error", "Error fetching questions", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping(value = "/quiz/all/{quizId}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId) {
        logger.info("Fetching questions for quiz with ID: {}", quizId);

        try {
            Quiz quiz = new Quiz();
            quiz.setqId(quizId);
            List<Question> questionsOfQuiz=    questionService.getQuestionsOfQuiz(quiz);
            ApiResponse<List<Question>> response = new ApiResponse<>("success", "Questions retrieved successfully", questionsOfQuiz);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching questions for quiz with ID {}: {}", quizId, e.getMessage());
            ApiResponse<List<Question>> response = new ApiResponse<>("error", "Error fetching questions", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Get a single question by ID.
     *
     * @param questionId the question ID
     * @return the question wrapped in a standardized response
     */
    @GetMapping(value="/{questionId}")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable("questionId") Long questionId) {
        logger.info("Fetching question with ID: {}", questionId);

        try {
            Question question = questionService.getQuestionById(questionId);
            ApiResponse<Question> response = new ApiResponse<>("success", "Question retrieved successfully", question);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching question with ID {}: {}", questionId, e.getMessage());
            ApiResponse<Question> response = new ApiResponse<>("error", "Error fetching question", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Delete a question by ID.
     *
     * @param questionId the question ID
     * @return a standardized response indicating the result of the deletion
     */
    // Delete Question
    @DeleteMapping( value="/{questionId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(@PathVariable("questionId") Long questionId) {
        logger.info("Deleting question with ID: {}", questionId);

        try {
            questionService.deleteQuestion(questionId);
            ApiResponse<Void> response = new ApiResponse<>("success", "Question deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error deleting question with ID {}: {}", questionId, e.getMessage());
            ApiResponse<Void> response = new ApiResponse<>("error", "Error deleting question", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/eval-quiz")
    public ResponseEntity<?> evaluationQuiz(@RequestBody List<Question> questions) {
        int marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;

        for (Question q : questions) {
            Question question = this.questionService.getQuestionById(q.getQuesId());

            // Check if the question was attempted
            if (q.getGivenAnswer() != null && !q.getGivenAnswer().trim().isEmpty()) {
                attempted++;

                // Check if the given answer is correct
                if (question.getAnswer().trim().equalsIgnoreCase(q.getGivenAnswer().trim())) {
                    correctAnswers++;
                    // Assuming each correct answer gives 1 mark, you can adjust as needed
                    marksGot += 1;
                }
            }
        }

        // Create a result object to return
        Map<String, Object> result = new HashMap<>();
        result.put("marksGot", marksGot);
        result.put("correctAnswers", correctAnswers);
        result.put("attempted", attempted);
        result.put("totalQuestions", questions.size());

        return ResponseEntity.ok(result);
    }



}
 