package com.example.officemanagement.network;

import com.example.officemanagement.model.Information;
import com.example.officemanagement.model.UserToken;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("auth/user/{id}/{pass}")
    Call<UserToken> signIn(@Path("id") String id, @Path("pass") String password);

    @GET("info/{id}")
    Call<Information> getInfo(@Path("id") String id);
}
