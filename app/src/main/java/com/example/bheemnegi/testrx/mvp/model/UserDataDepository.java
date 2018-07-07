package com.example.bheemnegi.testrx.mvp.model;

import com.example.bheemnegi.testrx.OnDataListener;
import com.example.bheemnegi.testrx.RestClient;
import com.example.bheemnegi.testrx.RestInterface;
import com.example.bheemnegi.testrx.User;
import com.example.bheemnegi.testrx.mvp.model.DataRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserDataDepository implements DataRepository {

    @Override
    public Disposable getUserData(final OnDataListener listener) {

        return RestClient.getInstance()
                .create(RestInterface.class).getUserdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {

                                   @Override
                                   public void onSuccess(List<User> users) {
                                       listener.onSuccess(users);
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       listener.onFailure(e.getMessage());
                                   }
                               }
                );
    }

}
