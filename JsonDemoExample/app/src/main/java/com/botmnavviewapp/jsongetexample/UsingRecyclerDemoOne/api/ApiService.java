package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.api;

import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/users?page=2")
    Call<UserResponse> getUsers();

}
