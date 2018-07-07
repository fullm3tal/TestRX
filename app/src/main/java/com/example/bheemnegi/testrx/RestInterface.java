package com.example.bheemnegi.testrx;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {

    @GET("/users")
    Single<List<User>> getUserdata();
}
