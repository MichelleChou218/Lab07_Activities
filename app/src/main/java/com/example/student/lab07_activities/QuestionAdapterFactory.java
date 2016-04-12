package com.example.student.lab07_activities;

import android.content.res.Resources;

/**
 * Created by student on 2016/4/11.
 */
public class QuestionAdapterFactory {
    private static QuestionAdapter adapter;

    private QuestionAdapterFactory(){

    }

    public static QuestionAdapter getQuestionAdapter() {
        if (adapter == null) {
            Resources res = MyApp.getContext().getResources();
            adapter = new QuestionFromStringResource(res);
        }
        return adapter;
    }


}
