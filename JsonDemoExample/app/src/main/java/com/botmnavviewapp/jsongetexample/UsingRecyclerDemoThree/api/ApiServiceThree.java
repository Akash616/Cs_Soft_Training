package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.api;

import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.modelclass.ColorResource;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceThree {

    @GET("/api/unknown")
    Call<ColorResource> getColors();

}
