package com.example.student.lab07_activities;

public class Activity2 extends QuestionActivity {
    @Override
    protected Class getBackActivityClass() {
        return Activity1.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return Activity3.class;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }
}
