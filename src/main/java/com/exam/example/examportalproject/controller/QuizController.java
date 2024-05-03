/*
 * Author Name:
 * Date: 12/16/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //aad quiz service

    @PostMapping("/")

    public ResponseEntity<Quiz>addQuiz(@RequestBody Quiz quiz){

        return new ResponseEntity<>(this.quizService.addQuiz(quiz), HttpStatus.OK);

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
 //get quizz by id

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
