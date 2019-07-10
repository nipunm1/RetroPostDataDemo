package com.example.retropostdatademo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetroClient {
    private static Retrofit retrofit = null;
    private static Retrofit init(){
     if(retrofit==null){
         retrofit = new Retrofit.Builder()
                 .baseUrl("http://api.targetpubg.com")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
     }
     return retrofit;
    }
    public static MyApi api = init().create(MyApi.class);
}
