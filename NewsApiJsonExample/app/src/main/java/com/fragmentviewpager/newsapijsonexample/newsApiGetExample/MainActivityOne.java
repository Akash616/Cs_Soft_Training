package com.fragmentviewpager.newsapijsonexample.newsApiGetExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.fragmentviewpager.newsapijsonexample.R;
import com.fragmentviewpager.newsapijsonexample.newsApiGetExample.adapter.NewsHeadlineAdapter;
import com.fragmentviewpager.newsapijsonexample.newsApiGetExample.api.ApiService;
import com.fragmentviewpager.newsapijsonexample.newsApiGetExample.modal.NewsRightNowModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityOne extends AppCompatActivity {

    RecyclerView rv_one;
    ProgressBar pb_one;
    public static final String BASE_URL = "https://newsapi.org/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        rv_one = findViewById(R.id.rv_one);
        pb_one = findViewById(R.id.pb_one);
        //------------------------------------------------------------------------------------------
        pb_one.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<NewsRightNowModal> call = service.getBusinessHeadlines();
        call.enqueue(new Callback<NewsRightNowModal>() {
            @Override
            public void onResponse(Call<NewsRightNowModal> call, Response<NewsRightNowModal> response) {
                Log.e("onResponse","Response Code"+response.code());
                pb_one.setVisibility(View.GONE);
                rv_one.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                NewsHeadlineAdapter newsHeadlineAdapter = new NewsHeadlineAdapter(MainActivityOne.this, response.body());
                rv_one.setAdapter(newsHeadlineAdapter);
            }

            @Override
            public void onFailure(Call<NewsRightNowModal> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
        //------------------------------------------------------------------------------------------

    }
}