package com.example.student.lab07_activities.adapter;

import android.content.res.Resources;
import android.text.Html;

import com.example.student.lab07_activities.model.Question;
import com.example.student.lab07_activities.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016/4/12.
 */
public class QuestionFromStringResource implements QuestionAdapter {

    private List<Question> list = new ArrayList<>();

    public QuestionFromStringResource(Resources res) {
        list.add(new Question(res.getString(R.string.question_1),
                res.getString(R.string.question_1_radio_a),
                res.getString(R.string.question_1_radio_b),
                res.getString(R.string.question_1_radio_c)));
        list.add(new Question(res.getString(R.string.question_2),
                res.getString(R.string.question_2_radio_a),
                res.getString(R.string.question_2_radio_b),
                res.getString(R.string.question_2_radio_c)));
        list.add(new Question(res.getString(R.string.question_3),
                res.getString(R.string.question_3_radio_a),
                res.getString(R.string.question_3_radio_b),
                res.getString(R.string.question_3_radio_c)));
    }

    @Override
    public int getQuestionCount() {
        return list.size();
    }

    @Override
    public CharSequence getQuestion(int index) {
        Question question = list.get(index);
        String text = question.getDescription();
        return Html.fromHtml(text);
    }

    @Override
    public CharSequence getQuestionOptionsA(int index) {
        return Html.fromHtml(list.get(index).getOptionA());
    }

    @Override
    public CharSequence getQuestionOptionsB(int index) {
        return Html.fromHtml(list.get(index).getOptionB());
    }

    @Override
    public CharSequence getQuestionOptionsC(int index) {
        return Html.fromHtml(list.get(index).getOptionC());
    }
}
