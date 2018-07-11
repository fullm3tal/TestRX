package com.example.bheemnegi.testrx.mvp.presenter;

import android.util.Log;

import com.example.bheemnegi.testrx.User;
import com.example.bheemnegi.testrx.mvp.view.ActivityView;
import com.example.bheemnegi.testrx.mvp.model.DataRepository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityPresenter {

    public WeakReference<ActivityView> activity;
    public DataRepository dataRepository;

    private CompositeDisposable mCompositeDisposable;

    private static final String TAG = "ActivityPresenter";

    public ActivityPresenter(WeakReference<ActivityView> activity, DataRepository dataRepository) {
        this.activity = activity;
        this.dataRepository = dataRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void loadDataOperatorMap() {

        dataRepository.getObservableUserData()
                .subscribeOn(Schedulers.io())

                .map(new Function<List<User>, List<String >>() {

                    @Override
                    public List<String> apply(List<User> userList) throws Exception {
                      List<String> namesList= new ArrayList<>();
                      Log.v(TAG,"Thread name: "+Thread.currentThread().getName()
                              +" Thread ID: "+Thread.currentThread().getId());
                      for(User user:userList){
                          namesList.add(user.getName());
                      }
                        return namesList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Log.v(TAG,"Thread name: "+Thread.currentThread().getName()
                                +" Thread ID: "+Thread.currentThread().getId());
                       getView().displayNamesOnly(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                     getView().displayError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG,"On Complete");
                    }
                });


    }

    public void loadDataFlowable(){

        dataRepository.getFlowableUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(List<User> userList) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void loadDataSingle(){

        mCompositeDisposable.add(dataRepository.getSingleUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> userList) {
                        if (userList.isEmpty()) {
                            getView().displayNoData();
                        } else {
                            getView().displayData(userList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().displayError(e.getMessage());
                        Log.v(TAG,"On Error called in loadDataSingle");
                    }
                }));


    }

    public void loadDataObservable() {

        dataRepository.getObservableUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                        Log.v(TAG,"On Subscribe called");
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        Log.v(TAG,"Next called in OnLoadObservable");
                        if (userList.isEmpty()) {
                            getView().displayNoData();
                        } else {
                            getView().displayData(userList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().displayError(e.getMessage());
                        Log.v(TAG, "Error: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG, "Completed called");
                    }
                });

    }

    public void loadDataMaybe(){

        dataRepository.getMaybeUserData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                        Log.v(TAG,"On Subscribe called");
                    }

                    @Override
                    public void onSuccess(List<User> userList) {
                        Log.v(TAG,"On Success called");
                        if (userList.isEmpty()) {
                            getView().displayNoData();
                        } else {
                            getView().displayData(userList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().displayError(e.getMessage());
                        Log.v(TAG, "Error: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG, "Completed called");
                    }
                }

                );

    }

    public void loadDataCompletable(){
        dataRepository.getCompletableUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                         Log.v(TAG,"List loading Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                       getView().displayError(e.getMessage());
                    }
                });
    }


    public ActivityView getView() {
        return activity.get();
    }

    public void unSubscribe() {
        mCompositeDisposable.dispose();
    }

}
