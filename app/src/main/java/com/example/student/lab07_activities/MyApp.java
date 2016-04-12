package com.example.student.lab07_activities;

import android.app.Application;
import android.content.Context;

/**
 * Created by student on 2016/4/12.
 */
public class MyApp extends Application{
    private static Context context;

    public MyApp() {
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
