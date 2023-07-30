package com.example.transline_assignment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mrkAttendence, updatePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mrkAttendence = findViewById(R.id.mrkAttendence);
        updatePhoto = findViewById(R.id.updatePhoto);

        mrkAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarkAttendenceActivity.class);
                startActivity(intent);
            }
        });

        updatePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadPhotoActivity.class);
                startActivity(intent);
            }
        });


    }
}