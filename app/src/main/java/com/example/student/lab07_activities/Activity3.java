package com.example.student.lab07_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
//    private TextView m_tv_main_activity_message;

    public static final String Q3_ANSWER_KEY = "Q3";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private Button m_radio_a;
    private Button m_radio_b;
    private Button m_radio_c;

    private CharSequence m_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        init();
    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        int index = 2;
        String no = String.valueOf(index + 1);
        m_tv_no.setText(no);

        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        m_tv_question.setText(adapter.getQuestion(index));
        m_radio_a.setText(adapter.getQuestionOptionA(index));
        m_radio_b.setText(adapter.getQuestionOptionB(index));
    }

    public void main(View view) {

    }

    public void back(View view) {
        finish();
    }

    public void clickAnswer(View view) {
        m_answer = view.getTag().toString();
    }
}
