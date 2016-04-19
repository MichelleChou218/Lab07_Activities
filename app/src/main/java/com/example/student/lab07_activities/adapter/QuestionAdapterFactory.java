package com.example.student.lab07_activities.adapter;

import android.content.res.Resources;
import android.util.Log;

import com.example.student.lab07_activities.myapp.MyApp;

public class QuestionAdapterFactory { // 轉接器工廠

    public interface CallBack{
        void receiveQuestionAdapter(QuestionAdapter adapter);
    }

    // 靜態成員
    private static QuestionAdapter adapter;

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

    // 私有建構子
    private QuestionAdapterFactory() { // 產生轉接器
    }

//    public static void getQuestionAdapter(CallBack callBack) {
//        loadFromGoogleDrive(callBack);
//    }

//    private static void loadFromGoogleDrive(final CallBack callBack) {
//        QuestionListService service = QuestionListService.retrofit.create(QuestionListService.class);
//        Call<QuestionList> call = service.getQuestionList();
//        call.enqueue(new Callback<QuestionList>() {
//            @Override
//            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
//
//            }
//        }
//    }
}