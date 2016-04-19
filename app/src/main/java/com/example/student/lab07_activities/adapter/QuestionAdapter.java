package com.example.student.lab07_activities.adapter;

/**
 * Created by student on 2016/4/11.
 */
public interface QuestionAdapter { // 轉接器
    int getQuestionCount();
    CharSequence getQuestion(int index);
    CharSequence getQuestionOptionsA(int index);
    CharSequence getQuestionOptionsB(int index);
    CharSequence getQuestionOptionsC(int index);

}
