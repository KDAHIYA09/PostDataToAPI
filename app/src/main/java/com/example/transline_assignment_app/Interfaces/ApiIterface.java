package com.example.transline_assignment_app.Interfaces;

import com.example.transline_assignment_app.Models.UploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiIterface {

    @Multipart
    @POST("/api:BS3FIkjh/upload/simpleimage")
    Call<UploadResponse> uploadImage(@Part MultipartBody.Part image,
                                     @Part("name") RequestBody name,
                                     @Part("type") RequestBody type,
                                     @Part("size") RequestBody size,
                                     @Part("mime") RequestBody mime,
                                     @Part("meta[width]") RequestBody width,
                                     @Part("meta[height]") RequestBody height);
}
