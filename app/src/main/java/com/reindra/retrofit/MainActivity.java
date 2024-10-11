package com.reindra.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reindra.retrofit.model.User;
import com.reindra.retrofit.model.UsersResponse;
import com.reindra.retrofit.service.UsersService;


import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputEditText nameedt, emailedt, passwordedt;
    Button savebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameedt = findViewById(R.id.name_edt);
        emailedt = findViewById(R.id.email_edt);
        passwordedt = findViewById(R.id.password_edt);
        savebtn = findViewById(R.id.save_btn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationData();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.escuelajs.co/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        UsersService service = retrofit.create(UsersService.class);
        Call<List<UsersResponse>> callSync = service.getTitle();

        callSync.enqueue(new Callback<List<UsersResponse>>() {


            @Override
            public void onResponse(Call<List<UsersResponse>> call, retrofit2.Response<List<UsersResponse>> response) {

                Log.d("TAG", "onsuccess: " +  response.body());
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());
            }
        });
    }

    private void validationData() {
        String name = nameedt.getText().toString().trim();
        String email = emailedt.getText().toString().trim();
        String password = passwordedt.getText().toString().trim();

        Log.d("TAG", "validationData: " + name + email + password);

        if (!name.isEmpty() || !email.isEmpty() || !password.isEmpty()){

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.escuelajs.co/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            UsersService service = retrofit.create(UsersService.class);
            Call<UsersResponse> callSync = service.createUser(name, email, password, "https://picsum.photos/800");

            callSync.enqueue(new Callback<UsersResponse>() {

                @Override
                public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                    Log.d("TAG", "createdata: "+response.body().getName());
                }

                @Override
                public void onFailure(Call<UsersResponse> call, Throwable t) {

                }
            });

        }
    }

}
