package com.soropromo.myapiapp.rest;

import com.soropromo.myapiapp.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserEndPoints {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String user);
}
