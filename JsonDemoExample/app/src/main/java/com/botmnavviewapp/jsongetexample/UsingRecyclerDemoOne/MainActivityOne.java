package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.botmnavviewapp.jsondemoexample.R;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.adapter.UserAdapter;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.api.ApiService;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.interfaceRecycler.RecyclerViewInterface;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass.UserResponse;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass.UsersDataList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityOne extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView idRecyclerView;
    ProgressBar idProgressBarOne;
    String url = "https://reqres.in/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        idRecyclerView = findViewById(R.id.idRecyclerView);
        idProgressBarOne = findViewById(R.id.idProgressBarOne);

        idProgressBarOne.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);



        Call<UserResponse> call = apiService.getUsers();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null && (response.code() == 200 || response.code() == 201)) {
                    idProgressBarOne.setVisibility(View.GONE);
                    UserResponse data = response.body();
                    setAdapter(data);
                    //String responseString = "First Name : " + data.getData().get(0).getFirst_name();
                    //tvDemo.setText(responseString); //tvDemo - TextView id

                    //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    //idRecyclerView.setLayoutManager(gridLayoutManager);


                } else {
                    Toast.makeText(getApplicationContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("onResponse", "Response Code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });

    }

    public void setAdapter(UserResponse data){
        idRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        UserAdapter adapter = new UserAdapter(getApplicationContext(), data,this );
        idRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(UsersDataList userResponse) {
        Intent intent = new Intent(MainActivityOne.this, SecondActivityOne.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Details", userResponse);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}