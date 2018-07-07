package com.example.bheemnegi.testrx.mvp.presenter;

import android.os.Bundle;

import com.example.bheemnegi.testrx.User;
import com.example.bheemnegi.testrx.mvp.view.ActivityView;
import com.example.bheemnegi.testrx.mvp.model.DataRepository;
import com.example.bheemnegi.testrx.OnDataListener;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ActivityPresenter {

    public WeakReference<ActivityView> activity;
    public DataRepository dataRepository;

    private CompositeDisposable mCompositeDisposable;

    public ActivityPresenter(WeakReference<ActivityView> activity, DataRepository dataRepository) {
        this.activity = activity;
        this.dataRepository = dataRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void loadData(){

       mCompositeDisposable.add(dataRepository.getUserData(new OnDataListener() {
            @Override
            public void onSuccess(List<User> userList) {
                if(userList.isEmpty()){
                    getView().displayNoData();
                }
                else {
                    getView().displayData(userList);
                }
            }

            @Override
            public void onFailure(String error) {
                   getView().displayError(error);
            }
        }));

    }

    public ActivityView getView(){
        return activity.get();
    }

    public void unSubscribe(){
      mCompositeDisposable.dispose();
    }

}
