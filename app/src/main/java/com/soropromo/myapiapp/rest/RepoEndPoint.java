package com.soropromo.myapiapp.rest;

import com.soropromo.myapiapp.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoEndPoint {
    @GET("/users/{user}/repos")
    Call<List<Repo>> getRepo(@Path("user") String name);

}
