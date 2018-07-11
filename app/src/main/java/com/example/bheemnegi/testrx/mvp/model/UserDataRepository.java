package com.example.bheemnegi.testrx.mvp.model;

import android.util.Log;

import com.example.bheemnegi.testrx.RestHelper;
import com.example.bheemnegi.testrx.User;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.completable.CompletableFromCallable;
import io.reactivex.internal.operators.flowable.FlowableFromCallable;
import io.reactivex.internal.operators.maybe.MaybeFromCallable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.internal.operators.single.SingleFromCallable;

public class UserDataRepository implements DataRepository {

    private static final String TAG = "UserDataRepository";

    @Override
    public Single<List<User>> getSingleUserData() {
        return new SingleFromCallable<>(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return RestHelper.getInstance().getUsersListFromNetwork().execute().body();
            }
        });
    }

    @Override
    public io.reactivex.Observable<List<User>> getObservableUserData() {
        return new ObservableFromCallable<>(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return RestHelper.getInstance().getUsersListFromNetwork().execute().body();
            }
        });
    }

    @Override
    public Maybe<List<User>> getMaybeUserData() {

       return new MaybeFromCallable<>(new Callable<List<User>>() {
           @Override
           public List<User> call() throws Exception {
               Log.v(TAG," Thread name "+Thread.currentThread().getName()+" Thread ID : "+Thread.currentThread().getId());
                return RestHelper.getInstance().getUsersListFromNetwork().execute().body();
           }
       });
    }

    @Override
    public Completable getCompletableUserData() {
        return new CompletableFromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return RestHelper.getInstance().getUsersListFromNetwork().execute().body();
            }
        });
    }

    @Override
    public io.reactivex.Flowable<List<User>> getFlowableUserData() {

        return new FlowableFromCallable<>(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                Log.v(TAG," Thread name "+Thread.currentThread().getName()+" Thread ID : "+Thread.currentThread().getId());
                return RestHelper.getInstance().getUsersListFromNetwork().execute().body();
            }
        });



    }
}
