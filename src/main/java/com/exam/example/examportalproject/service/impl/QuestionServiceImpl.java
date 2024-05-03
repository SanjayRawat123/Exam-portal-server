/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

import com.exam.example.examportalproject.model.category.Question;
import com.exam.example.examportalproject.model.category.Quiz;
import com.exam.example.examportalproject.repository.QuestionRepository;
import com.exam.example.examportalproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestion() {
        return new HashSet<>(this.questionRepository.findAll());

    }

    @Override
    public Question getQuestionBy(Long quesId) {

        return questionRepository.findById(quesId).get();
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {

        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQustion(Long quesId) {
        Question question= new Question();
        question.setQuesId(quesId);
        this.questionRepository.delete(question);
    }


}
