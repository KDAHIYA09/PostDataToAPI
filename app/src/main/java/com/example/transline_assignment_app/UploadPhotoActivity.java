package com.example.transline_assignment_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.transline_assignment_app.Interfaces.ApiIterface;
import com.example.transline_assignment_app.Models.RealPathUtil;
import com.example.transline_assignment_app.Models.UploadResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadPhotoActivity extends AppCompatActivity {
    private ImageView img;
    Button btnselectimg, btnUploadImg;
    String path;
    private static final String BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/";
    private int imageLength = 300;  // since we have taken fix size for any image we can specify length & width of image before
    private int imageWidth = 300;
    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private static final int REQUEST_IMAGE_PICK = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);

        img = findViewById(R.id.imageView);
        btnselectimg = findViewById(R.id.btnChange);
        btnUploadImg = findViewById(R.id.btnUpload);

        btnselectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,10);
                }
                else
                {
                    ActivityCompat.requestPermissions(UploadPhotoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        });

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == Activity.RESULT_OK)
        {
            Uri uri = data.getData();
            Context context = UploadPhotoActivity.this;
            path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            img.setImageBitmap(bitmap);
        }

    }


    private void uploadImage() {


        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        File file = new File(String.valueOf(img));

        // Create a request body for the image file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // Create MultipartBody.Part from the image file
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName() , requestFile);

        // Create RequestBody instances for other parameters
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "Sample_Image.png");
        RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "image");
        RequestBody size = RequestBody.create(MediaType.parse("text/plain"), "797069");
        RequestBody mime = RequestBody.create(MediaType.parse("text/plain"), "image/png");
        RequestBody width = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(imageWidth));
        RequestBody height = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(imageLength));

        // Create the ApiService instance
        ApiIterface apiService = retrofit.create(ApiIterface.class);


        // Make the API call
        Call<UploadResponse> call = apiService.uploadImage(body, name, type, size, mime, width, height);

        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    UploadResponse uploadResponse = response.body();
                    if (uploadResponse != null) {
                        // Image upload successful. Handle the server response here.
                        String path = uploadResponse.getPath();
                        String name = uploadResponse.getName();
                        int size = uploadResponse.getSize();
                        String mime = uploadResponse.getMime();
                        int width = uploadResponse.getMeta().getWidth();
                        int height = uploadResponse.getMeta().getHeight();
                        Toast.makeText(UploadPhotoActivity.this, "Upload Succesfull", Toast.LENGTH_SHORT).show();
                        Log.d("UPLOAD", "Path: " + path);
                        Log.d("UPLOAD", "Name: " + name);
                        Log.d("UPLOAD", "Size: " + size);
                        Log.d("UPLOAD", "Mime: " + mime);
                        Log.d("UPLOAD", "Width: " + width);
                        Log.d("UPLOAD", "Height: " + height);
                    }
                    else {
                        // Image upload failed. Handle the server response here.
                        Log.d("UPLOAD", "Image upload failed.");
                    }
                } else {
                    // Image upload failed due to a network error or other issues.
                    Log.d("UPLOAD", "Image upload failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadResponse> call, @NonNull Throwable t) {
                // Image upload failed due to a network error or other issues.
                Log.d("UPLOAD", "Image upload failed.");
            }
        });
    }




}