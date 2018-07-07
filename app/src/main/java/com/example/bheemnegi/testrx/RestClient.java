package com.example.bheemnegi.testrx;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static Retrofit retrofit = null;

    private RestClient() {

    }

    public static Retrofit getInstance() {
        synchronized (RestClient.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(AppUrl.DUMMY_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }

        return retrofit;
    }
}
