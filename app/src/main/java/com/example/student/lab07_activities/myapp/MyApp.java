package com.example.student.lab07_activities.myapp;

import android.app.Application;
import android.content.Context;

import com.example.student.lab07_activities.model.UserAnswers;

/**
 * Created by student on 2016/4/12.
 */
public class MyApp extends Application{
    private static Context context;
    private static UserAnswers UserAnswers;

    public static Context getContext() {
        return context;
    }

    public static UserAnswers getuserAnswers() {
        if (UserAnswers == null) {
            UserAnswers = new UserAnswers(3);
        }
        return UserAnswers;
    }

    public MyApp() {
        context = this;
    }
}
