package com.example.retropostdatademo.api;

import com.example.retropostdatademo.data.model.req.LoginReq;
import com.example.retropostdatademo.data.model.res.LoginRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApi {
    @Headers("Content-Type: application/json")
    @POST("/user/login")
    Call<LoginRes> login(@Body LoginReq req);
}
