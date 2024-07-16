package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.category.Category;
import com.exam.example.examportalproject.model.category.Quiz;

import java.util.List;

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

    /**
     * Get list of quizzes  by category.
     *
     * @param category  of the quiz.
     *
     *  @return the Quiz list for given category.
     */

    public  List<Quiz> getQuizzesOfCategory (Category category);

    /**
     * Get list of Active quizzes.
     *
     * @return the list of active quizzez
     */

    List<Quiz> getActiveQuizzes();

    /**
     * Retrieves list of Active quizzes of category .
     *@param category the quiz of category
     * @return the list of active quizzez for given category;
     */

    List<Quiz> getActiveQuizzesOfCategory(Category category);
}
