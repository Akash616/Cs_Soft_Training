package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.botmnavviewapp.jsondemoexample.R;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.adapter.ColorAdapter;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.api.ApiServiceThree;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.modelclass.ColorResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityThree extends AppCompatActivity {

    RecyclerView idRecyclerViewThree;
    ProgressBar idProgressBarThree;
    public static final String BASE_URL = "https://reqres.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);

        idProgressBarThree = findViewById(R.id.idProgressBarThree);
        idRecyclerViewThree = findViewById(R.id.idRecyclerViewThree);
        //-------------------------------------------------------------------------------------------------------------------
        idProgressBarThree.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServiceThree apiServiceThree = retrofit.create(ApiServiceThree.class);
        Call<ColorResource> call = apiServiceThree.getColors();
        call.enqueue(new Callback<ColorResource>() {
            @Override
            public void onResponse(Call<ColorResource> call, Response<ColorResource> response) {

                if(response.body()!=null && (response.code()==200 || response.code()==201)){
                    Log.e("onResponse","Response Code : "+response.code());
                    idProgressBarThree.setVisibility(View.GONE);
                    idRecyclerViewThree.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ColorAdapter adapter = new ColorAdapter(MainActivityThree.this, response.body());
                    idRecyclerViewThree.setAdapter(adapter);

                }else{
                    Toast.makeText(getApplicationContext(), "Response Code : "+response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("onResponse","Response Code : "+response.code());
                }

            }

            @Override
            public void onFailure(Call<ColorResource> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });

    }
}