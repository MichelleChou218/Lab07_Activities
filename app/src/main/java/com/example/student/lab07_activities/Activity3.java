package com.example.student.lab07_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.example.student.lab07_activities.adapter.QuestionAdapter;
import com.example.student.lab07_activities.adapter.QuestionAdapterFactory;
import com.example.student.lab07_activities.model.UserAnswers;
import com.example.student.lab07_activities.myapp.MyApp;

public class Activity3 extends QuestionActivity {

    protected void onStart() {
        super.onStart();
        setNextButtonText("Finish");
    }

    @Override
    protected Class getBackActivityClass() {
        return Activity2.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return null;
    }

    @Override
    protected int getBackButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    public void next(View view) {
        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        UserAnswers userAnswers = MyApp.getuserAnswers();
        StringBuilder message = new StringBuilder();

        message.append("您的作答如下\n\n");
        for (int i=0; i<adapter.getQuestioncount(); i++) {
            message.append(String.valueOf(i+1))
                    .append(": ")
                    .append(userAnswers.getAnswer(i))
                    .append("\n")
                    .append(userAnswers.getDescriptions(i))
                    .append("\n");
        }
        message.append("\n確定要結束?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        QuestionActivity.resetQuestionIndex();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .show();
    }
}
