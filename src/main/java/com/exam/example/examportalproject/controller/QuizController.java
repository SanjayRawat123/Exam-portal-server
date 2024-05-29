/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/quiz")
@CrossOrigin("*")
@Validated
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
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Quiz>> addQuiz(@RequestBody Quiz quiz) {
        logger.info("Adding new category: {}", quiz.getTitle());
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

    // update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz>update(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

   // get quizzes
    @GetMapping("/")
    public ResponseEntity<?>getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());

    }


    @GetMapping("/{qId}")
    public Quiz quiz (@PathVariable("qId") Long qid){

        return this.quizService.getQuizById(qid);

    }


    //delet quiz

    @DeleteMapping( value = "/{qId}")

    public void delete(@PathVariable("qId") Long qId){
        this.quizService.deleteQuiz(qId);
    }

}
