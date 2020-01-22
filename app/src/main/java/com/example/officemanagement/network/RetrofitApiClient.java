package com.example.officemanagement.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    public static final String BASE_URL = "https://office-managment-backend.herokuapp.com/api/v1/";

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder().setLenient().create();

    private RetrofitApiClient() {
    }

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            synchronized (RetrofitApiClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                }
            }
        }
        return retrofit;
    }
}
