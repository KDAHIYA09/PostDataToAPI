package com.example.transline_assignment_app.Interfaces;

import com.example.transline_assignment_app.Models.DataModal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("/api:BS3FIkjh/attendance")
    Call<DataModal> createPost(@Body DataModal dataModal);

}
