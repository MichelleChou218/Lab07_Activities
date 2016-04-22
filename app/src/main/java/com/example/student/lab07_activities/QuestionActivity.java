package com.example.student.lab07_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.student.lab07_activities.adapter.QuestionAdapter;
import com.example.student.lab07_activities.adapter.QuestionAdapterFactory;
import com.example.student.lab07_activities.model.UserAnswers;
import com.example.student.lab07_activities.myapp.MyApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class QuestionActivity extends AppCompatActivity
        implements QuestionAdapterFactory.Receiver{

    public static final String FILENAME = "QuestionActivity_UserAnswers";
    private static final String TAG = "QuestionActivity";
    private static UserAnswers sUserAnswers;

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private Button m_btn_back;
    private Button m_btn_next;

    private static int sQuestionIndex = 0;
    private static QuestionAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initQuestions();
        initBackNextButtons();
        Log.d(this.toString(), "onCreate, index = " +sQuestionIndex);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void sabeData() {
        Log.d(TAG, "saveData() serialize");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeInt(sQuestionIndex);
            oos.writeInt(sLastQuestionIndex);
            oos.writeObject(sUserAnswers);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
    }

    private void restoreData() {
        Log.d(TAG, "restoreData() deserialize");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            sQuestionIndex = ois.readInt();
            sLastQuestionIndex = ois.readInt();
            sUserAnswers = (UserAnswers) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    Log.d(TAG, e.toString());
                }
            }
        }
    }

    @SuppressWarnings("ResourceType")
    private void initBackNextButtons() {
        m_btn_back = (Button)findViewById(R.id.btn_back);
        m_btn_next = (Button)findViewById(R.id.btn_next);

        m_btn_back.setVisibility(getBackButtonVisibility());
        m_btn_next.setVisibility(getNextButtonVisibility());
    }

    private void initQuestions() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        String no = String.valueOf(sQuestionIndex + 1);
        m_tv_no.setText(no);

        if (sAdapter == null) {
            Log.d("sAdapter", "null");
            QuestionAdapterFactory.getQuestionAdapter(this);
        }
        updateQuestionText();
    }

    @Override
    public void receiveQuestionAdapter(QuestionAdapter adapter) {
        sAdapter = adapter;
        findViewById(R.id.pgb_loading).setVisibility(View.GONE);
        updateQuestionText();
    }

    private void updateQuestionText() {
        if (sAdapter == null){
            return;
        }

        findViewById(R.id.pgb_loading).setVisibility(View.GONE);

        m_tv_question.setText(Html.fromHtml(sAdapter.
                getQuestion(sQuestionIndex).toString()));
        m_radio_a.setText(Html.fromHtml(sAdapter.
                getQuestionOptionsA(sQuestionIndex).toString()));
        m_radio_b.setText(Html.fromHtml(sAdapter.
                getQuestionOptionsB(sQuestionIndex).toString()));
        m_radio_c.setText(Html.fromHtml(sAdapter.
                getQuestionOptionsC(sQuestionIndex).toString()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.toString(), "onStart, index = " + sQuestionIndex);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.toString(), "onResume, index = " + sQuestionIndex);
        if (sQuestionIndex < sLastQuestionIndex) {
            overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
        } else if (sQuestionIndex > sLastQuestionIndex) {
            overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.toString(), "onPause, index = " + sQuestionIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.toString(), "onStop, index = " + sQuestionIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.toString(), "onDestroy, index = " + sQuestionIndex);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(this.toString(), "onRestart, index = " + sQuestionIndex);
    }




    private static int sLastQuestionIndex;

    public void back(View view) {
        sLastQuestionIndex = sQuestionIndex;
        sQuestionIndex--;
        Intent intent = new Intent(this, getBackActivityClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

//        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }


    public void click(View view) {
        RadioButton radio = (RadioButton)view;
        UserAnswers userAnswers = MyApp.getUserAnswers();
        switch (radio.getId()) {
            case R.id.radio_a :
                userAnswers.setAnswer(sQuestionIndex, 'A', radio.getText());
                Log.d("QuestionIndex", "questionIndex = " +sLastQuestionIndex +" 選了A");
                break;
            case R.id.radio_b :
                userAnswers.setAnswer(sQuestionIndex, 'B', radio.getText());
                Log.d("QuestionIndex", "questionIndex = " +sLastQuestionIndex +" 選了B");
                break;
            case R.id.radio_c :
                userAnswers.setAnswer(sQuestionIndex, 'C', radio.getText());
                Log.d("QuestionIndex", "questionIndex = " +sLastQuestionIndex +" 選了C");
                break;
        }
    }

    public void next(View view) {
        sLastQuestionIndex = sQuestionIndex;
        sQuestionIndex++;

        Intent intent = new Intent(this, getNextActivityClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

//        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }



    @Override
    public void onBackPressed() {
        return;
    }

    public void setBackButtonText(CharSequence text) {
        m_btn_back.setText(text);
    }

    public void setNextButtonText(CharSequence text) {
        m_btn_next.setText(text);
    }

    protected abstract Class getBackActivityClass();
    protected abstract Class getNextActivityClass();
    protected abstract int getBackButtonVisibility();
    protected abstract int getNextButtonVisibility();

    public static final int VISIBLE = View.VISIBLE;
    public static final int INVISIBLE = View.INVISIBLE;
    public static final int GONE = View.GONE;

    @IntDef({VISIBLE, INVISIBLE, GONE})
    public @interface Visibility {
    }

    public static void resetQuestionIndex() {
        sQuestionIndex =0;
        sLastQuestionIndex = 0;
    }

}
