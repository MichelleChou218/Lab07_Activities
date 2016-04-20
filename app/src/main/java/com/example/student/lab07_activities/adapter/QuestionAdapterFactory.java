package com.example.student.lab07_activities.adapter;

import android.content.res.Resources;
import android.util.Log;

import com.example.student.lab07_activities.model.Question;
import com.example.student.lab07_activities.model.QuestionList;
import com.example.student.lab07_activities.model.QuestionListService;
import com.example.student.lab07_activities.myapp.MyApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAdapterFactory { // 轉接器工廠

    public interface Receiver{
        void receiveQuestionAdapter(QuestionAdapter adapter);
    }

    // 靜態成員
    private static QuestionAdapter adapter;

    //舊版
    public static QuestionAdapter getQuestionAdapter() {
        if(adapter == null) {
            // 透過 MyApp 取得 Context，進一步取得 Resources
            Resources res = MyApp.getContext().getResources();
            //            adapter = new QuestionFromStringResource(res);
            try {
                adapter = new QuestionFromRawXml(res);

            } catch(Exception e) {
                Log.d("factory",e.toString());
            }
        }
        return adapter;
    }

    //新版
    public static void getQuestionAdapter(Receiver receiver) {
        loadFromGoogleDrive(receiver);
    }

    private static void loadFromGoogleDrive(final Receiver receiver) {
        QuestionListService service = QuestionListService.retrofit.create(QuestionListService.class);
        Call<QuestionList> call = service.getQuestionList();
        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "onResponse() success");
                    QuestionList ql = response.body();
                    List<Question> list = ql.getList();
                    adapter = new QuestionFromGoogleDriveXML(list);
                    receiver.receiveQuestionAdapter(adapter);
                } else {
                    Log.d("Retrofit", "onResponse() : error response, no access to resource ?");
                }
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                Log.d("Retrofit", "onFailure() : " + t.toString());
            }
        });
    }

    private QuestionAdapterFactory() {

    }
}