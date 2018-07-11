package com.example.bheemnegi.testrx;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bheemnegi.testrx.mvp.model.UserDataRepository;
import com.example.bheemnegi.testrx.mvp.presenter.ActivityPresenter;
import com.example.bheemnegi.testrx.mvp.view.ActivityView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ActivityView {

    @BindView(R.id.tv_display_user_size)
    TextView textViewShowEmptyListOrErrorMessage;

    @BindView(R.id.pb_network_call)
    ProgressBar progressBar;

    @BindView(R.id.tv_loading_data_message)
    TextView textViewLoadingDataMessage;

    @BindView(R.id.bt_start_network_call)
    Button buttonNetworkCall;

    /*@BindView(R.id.list_view_users)
    ListView listView;*/

    @BindView(R.id.recycler)
    RecyclerView recycler;

    private Context mContext = MainActivity.this;


    private static final String TAG = "MainActivity";

    private ActivityPresenter activityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void init() {
        WeakReference<ActivityView> view = new WeakReference<ActivityView>(this);
        activityPresenter = new ActivityPresenter(view, new UserDataRepository());
    }

    @OnClick(R.id.bt_start_network_call)
    public void submitButton(View view) {
        showProgressBar();
        activityPresenter.loadDataOperatorMap();
    }

    private void showProgressBar() {

        recycler.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        textViewShowEmptyListOrErrorMessage.setVisibility(View.INVISIBLE);
        textViewLoadingDataMessage.setVisibility(View.VISIBLE);
        buttonNetworkCall.setVisibility(View.INVISIBLE);
    }

    public void showEmptyListOrErrorMessage() {
        textViewShowEmptyListOrErrorMessage.setText("");
        progressBar.setVisibility(View.INVISIBLE);
        textViewShowEmptyListOrErrorMessage.setVisibility(View.VISIBLE);
        textViewLoadingDataMessage.setVisibility(View.INVISIBLE);
        buttonNetworkCall.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
    }

    public void showListOfUsers() {
        progressBar.setVisibility(View.INVISIBLE);
        textViewShowEmptyListOrErrorMessage.setVisibility(View.INVISIBLE);
        textViewLoadingDataMessage.setVisibility(View.INVISIBLE);
        buttonNetworkCall.setVisibility(View.INVISIBLE);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayData(List<User> userList) {
        showListOfUsers();
        CustomRecyclerAdapter customAdapter = new CustomRecyclerAdapter(this, userList);
        recycler.setAdapter(customAdapter);
    }

    @Override
    public void displayNoData() {
        showEmptyListOrErrorMessage();
        textViewLoadingDataMessage.setVisibility(View.VISIBLE);
        textViewShowEmptyListOrErrorMessage.setText(R.string.empty_list_network_call);
    }

    @Override
    public void displayError(String error) {
        showEmptyListOrErrorMessage();
        textViewShowEmptyListOrErrorMessage.setText(error);
    }

    @Override
    public void displayNamesOnly(List<String> namesList) {
        showListOfUsers();
        List<User> userList= new ArrayList<>();
        for (String name: namesList){
            userList.add(new User(1,name,"Metal","metal@metal.com"));
        }
        CustomRecyclerAdapter customAdapter = new CustomRecyclerAdapter(this, userList);
        recycler.setAdapter(customAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        activityPresenter.unSubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}