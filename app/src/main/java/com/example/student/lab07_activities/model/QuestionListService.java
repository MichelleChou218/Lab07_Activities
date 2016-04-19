package com.example.student.lab07_activities.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

/**
 * Created by student on 2016/4/19.
 */
public interface QuestionListService {
    @GET("uc?export=down&id=0BwIdzAjvQ8FwWG5HZUxIZHJOd0k")
    Call<QuestionList>getQuestionList();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
}
