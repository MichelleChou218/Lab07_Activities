package com.example.student.lab07_activities.model;

/**
 * Created by student on 2016/4/12.
 */
public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;

    public Question(String question, String optionA, String optionB, String optionC) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }
}
