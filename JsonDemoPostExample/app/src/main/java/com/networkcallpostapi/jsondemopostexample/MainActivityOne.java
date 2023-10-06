package com.networkcallpostapi.jsondemopostexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.networkcallpostapi.jsondemopostexample.modalClass.DataModal;
import com.networkcallpostapi.jsondemopostexample.myInterface.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityOne extends AppCompatActivity {

    private ProgressBar pb_loading;
    private Button btn_dataToApi;
    private EditText et_email, et_password;
    private TextView tv_response;
    String url = "https://reqres.in/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        pb_loading = findViewById(R.id.pb_loading);
        btn_dataToApi = findViewById(R.id.btn_dataToApi);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        tv_response = findViewById(R.id.tv_response);

        btn_dataToApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (et_email.getText().toString().isEmpty() && et_password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivityOne.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                PostData(et_email.getText().toString(), et_password.getText().toString());
            }
        });

    }

    private void PostData(String email, String password) {
        pb_loading.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal dataModal = new DataModal(email, password);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(dataModal);

        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivityOne.this, "Data added to API", Toast.LENGTH_SHORT).show();
                // below line is for hiding our progress bar.
                pb_loading.setVisibility(View.GONE);
                // on below line we are setting empty text
                // to our both edit text.
                et_email.setText("");
                et_password.setText("");
                // we are getting response from our body
                // and passing it to our modal class.
                DataModal responseFromAPI = response.body();
                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nEmail : " + responseFromAPI.getEmail()
                        + "\nPassword : " + responseFromAPI.getPassword();
                Log.e("onResponse","Response Email : "+response.body().getEmail());
                tv_response.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Log.e("onFailure","Error : "+t.getMessage());
                tv_response.setText("Error found is : " + t.getMessage());
            }
        });

    }
}