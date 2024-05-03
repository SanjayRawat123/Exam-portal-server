/*
 * Author Name:
 * Date: 12/15/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long qId;

    private String title;

    private String description;

    private String maxMarks;

    private String numOfQuestions;

    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions=new HashSet<>();
    public Quiz() {
    }

    public long getqId() {
        return qId;
    }

    public Quiz setqId(long qId) {
        this.qId = qId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Quiz setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Quiz setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public Quiz setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
        return this;
    }

    public String getNumOfQuestions() {
        return numOfQuestions;
    }

    public Quiz setNumOfQuestions(String numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Quiz setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Quiz setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Quiz setQuestions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }
}
