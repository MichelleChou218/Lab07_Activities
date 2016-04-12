package com.example.student.lab07_activities;

/**
 * Created by student on 2016/4/11.
 */
public interface QuestionAdapter {
    int getQuestioncount();
    CharSequence getQuestion(int index);
    CharSequence getQuestionOptionA(int index);
    CharSequence getQuestionOptionB(int index);
    CharSequence getQuestionOptionC(int index);
}
