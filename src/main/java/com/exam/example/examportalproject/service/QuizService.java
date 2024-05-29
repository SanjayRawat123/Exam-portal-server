package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Quiz;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Set;

public interface QuizService {

    /**
     * Adds a new quiz.
     *
     * @param quiz the quiz to be added
     * @return the saved quiz
     */
    public Quiz addQuiz(Quiz quiz);

    /**
     * Updates an existing quiz.
     *
     * @param quiz the quiz to be updated
     * @return the updated quiz
     */
    public Quiz updateQuiz(Long qId ,Quiz quiz);


    /**
     * Retrieves all Quiz.
     *
     * @return a list of all Quiz
     */
    public List<Quiz> getQuizzes();

    /**
     * Retrieves a quiz by its ID.
     *
     * @param qId the ID of the quiz to retrieve
     * @return the Quiz with the specified ID
     */
    public Quiz getQuizById(Long qId);


    /**
     * Deletes a quiz by its ID.
     *
     * @param qId the ID of the quiz to delete
     */

    public void deleteQuiz(Long qId);



}
