package com.fragmentviewpager.newsapijsonexample.newsApiGetExample.api;

import com.fragmentviewpager.newsapijsonexample.newsApiGetExample.modal.NewsRightNowModal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("top-headlines?country=us&category=business&apiKey=f70aa87b62f1414ea83b4b1226fed50f")
    Call<NewsRightNowModal> getBusinessHeadlines();

}
