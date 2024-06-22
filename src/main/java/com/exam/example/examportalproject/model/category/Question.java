/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.model.category;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quesId;

    @Column(length = 5000)
    private String content;

    private String image;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    public Question() {
    }

    public Question(long quesId, String content, String image, String option1, String option2, String option3, String option4, String answer, Quiz quiz) {
        this.quesId = quesId;
        this.content = content;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.quiz = quiz;
    }

    public long getQuesId() {
        return quesId;
    }

    public Question setQuesId(long quesId) {
        this.quesId = quesId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Question setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Question setImage(String image) {
        this.image = image;
        return this;
    }

    public String getOption1() {
        return option1;
    }

    public Question setOption1(String option1) {
        this.option1 = option1;
        return this;
    }

    public String getOption2() {
        return option2;
    }

    public Question setOption2(String option2) {
        this.option2 = option2;
        return this;
    }

    public String getOption3() {
        return option3;
    }

    public Question setOption3(String option3) {
        this.option3 = option3;
        return this;
    }

    public String getOption4() {
        return option4;
    }

    public Question setOption4(String option4) {
        this.option4 = option4;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Question setQuiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }
}
