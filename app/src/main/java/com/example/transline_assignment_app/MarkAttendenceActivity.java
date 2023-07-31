package com.example.transline_assignment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transline_assignment_app.Interfaces.RetrofitAPI;
import com.example.transline_assignment_app.Models.DataModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarkAttendenceActivity extends AppCompatActivity {
    ImageView img1;
    private Button postDataBtn;
    private TextView responseTV;
    private ProgressBar loadingPB;

    // Here we have taken constant parameters given in api for uploading

    private String parameter1 = "14";
    private String parameter2 = "1690603292851";
    private String parameter4 = "23A Shivaji Marg, Karampura Industrial Area, Karam Pura, Delhi, 110015";
    private String parameter3 = "28.661130,77.148360";
    private String parameter5 = "15-07-2023 12:12:54";
    private String parameter6 = "attendance test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendence);

        postDataBtn = findViewById(R.id.idBtnPost);
        responseTV = findViewById(R.id.idTVResponse);
        loadingPB = findViewById(R.id.idLoadingPB);
        img1 = findViewById(R.id.back);

        getSupportActionBar().hide();

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkAttendenceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postData(parameter1, parameter2, parameter3, parameter4, parameter5, parameter6);
            }
        });

    }

    private void postData(String id, String created_at, String latlng, String location, String actDate, String remark) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://x8ki-letl-twmt.n7.xano.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        DataModal modal = new DataModal(id, created_at, latlng, location, actDate, remark);

        Call<DataModal> call = retrofitAPI.createPost(modal);


        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                Toast.makeText(MarkAttendenceActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                loadingPB.setVisibility(View.GONE);

                DataModal responseFromAPI = response.body();

                String responseString = "Response Code : " + response.code() + "\nid : " + responseFromAPI.getId() + "\n" + "created_at : " + responseFromAPI.getCreated_at()
                        + "\nlatitude & longitude : " + responseFromAPI.getLatlng() + "\nlocation : " + responseFromAPI.getLocation()
                        + "\nDate & Time : " + responseFromAPI.getActDate() + "\nremarks : " + responseFromAPI.getRemark();

                responseTV.setText(responseString);
                Toast.makeText(MarkAttendenceActivity.this, "Attendence Marked Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }


}