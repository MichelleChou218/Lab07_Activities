package com.example.student.lab07_activities.adapter;

import com.example.student.lab07_activities.model.Question;

import java.util.List;

/**
 * Created by student on 2016/4/20.
 */
public class QuestionFromGoogleDriveXML implements QuestionAdapter {

    private List<Question> list;

    public QuestionFromGoogleDriveXML(List<Question> list) {
        this.list = list;
    }

    @Override
    public int getQuestionCount() {
        return list.size();
    }

    @Override
    public CharSequence getQuestion(int index) {
        return list.get(index).getDescription();
    }

    @Override
    public CharSequence getQuestionOptionsA(int index) {
        return list.get(index).getOptionA();
    }

    @Override
    public CharSequence getQuestionOptionsB(int index) {
        return list.get(index).getOptionB();
    }

    @Override
    public CharSequence getQuestionOptionsC(int index) {
        return list.get(index).getOptionC();
    }
}


