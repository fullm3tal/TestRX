package com.example.bheemnegi.testrx.mvp.model;

import com.example.bheemnegi.testrx.OnDataListener;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface DataRepository {
    public Disposable getUserData(OnDataListener listener);
}
