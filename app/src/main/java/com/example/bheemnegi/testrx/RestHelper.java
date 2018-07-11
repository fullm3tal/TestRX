package com.example.bheemnegi.testrx;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestHelper {

    private static Retrofit retrofit = null;

    private RestHelper() {

    }

    public static RestInterface getInstance() {
        synchronized (RestHelper.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(AppUrl.DUMMY_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }

        return retrofit.create(RestInterface.class);
    }

}
