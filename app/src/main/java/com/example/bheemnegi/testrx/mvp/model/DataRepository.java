package com.example.bheemnegi.testrx.mvp.model;

import com.example.bheemnegi.testrx.OnDataListener;
import com.example.bheemnegi.testrx.User;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public interface DataRepository {
    public Single<List<User>> getSingleUserData();

    public io.reactivex.Observable<List<User>> getObservableUserData();

    public Maybe<List<User>> getMaybeUserData();

    io.reactivex.Flowable<List<User>> getFlowableUserData();

    Completable getCompletableUserData();

    io.reactivex.Observable<User> getTakeableUserData();
}
