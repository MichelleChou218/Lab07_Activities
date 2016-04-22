package com.example.student.lab07_activities.model;

import java.io.Serializable;

/**
 * Created by student on 2016/4/15.
 */
public class UserAnswers implements Serializable {
    private char[] answers;
    private CharSequence[] descriptions;

    public UserAnswers(int questionCount) {
        answers = new char[questionCount];
        descriptions = new CharSequence[questionCount];
    }

    public void setAnswer(int index, char answer, CharSequence description) {
        answers[index] = answer;
        descriptions[index] = description;
    }

    public char getAnswer (int index) {
        return answers[index];
    }

    public CharSequence getDescriptions(int index) {
        return descriptions[index];
    }
}
