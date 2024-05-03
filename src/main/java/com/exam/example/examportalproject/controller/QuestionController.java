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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping( value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    //Add Question

    @PostMapping(value = "/")

    public ResponseEntity<Question>addQuestion (@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }


    //update

    @PutMapping ("/")

    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));

    }

    //get all Question of any Question

    @GetMapping("/quiz/{qId}")

    public ResponseEntity<?> detQustionOfQuiz(@PathVariable("qId") Long qId ){

       // Quiz quiz = new Quiz();
        //quiz.setqId(qId);
        //Set<Question> questionOfQuiz =this.questionService.getQuestionOfQuiz(quiz);
        //return ResponseEntity.ok(questionOfQuiz);

     Quiz quiz =   this.quizService.getQuizById(qId);

       Set<Question>questions= quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumOfQuestions()));
        {
        list = list.subList(0,Integer.parseInt(quiz.getNumOfQuestions()+1));
    }
        Collections.shuffle(list);
    return ResponseEntity.ok(list);
    }

    //get single question
    @GetMapping("/{quesId}")

    public Question get(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestionBy(quesId);
    }


    //delete question

    @DeleteMapping("/quesId")
    public void delete(@PathVariable("quesId")Long quesId){

        this.questionService.deleteQustion(quesId);
    }


}
