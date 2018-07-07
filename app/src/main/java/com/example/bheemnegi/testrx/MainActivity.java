package com.example.bheemnegi.testrx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bheemnegi.testrx.mvp.model.UserDataDepository;
import com.example.bheemnegi.testrx.mvp.presenter.ActivityPresenter;
import com.example.bheemnegi.testrx.mvp.view.ActivityView;

import java.lang.ref.WeakReference;
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

    @BindView(R.id.list_view_users)
    ListView listView;

    private static final String TAG = "MainActivity";

    private ActivityPresenter activityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void init() {
        WeakReference<ActivityView> view = new WeakReference<ActivityView>(this);
        activityPresenter = new ActivityPresenter(view, new UserDataDepository());
    }

    @OnClick(R.id.bt_start_network_call)
    public void submitButton(View view) {
        showProgressBar();
        activityPresenter.loadData();
    }


    private void showProgressBar() {
        listView.setVisibility(View.INVISIBLE);
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
        listView.setVisibility(View.INVISIBLE);
    }

    public void showListOfUsers() {
        progressBar.setVisibility(View.INVISIBLE);
        textViewShowEmptyListOrErrorMessage.setVisibility(View.INVISIBLE);
        textViewLoadingDataMessage.setVisibility(View.INVISIBLE);
        buttonNetworkCall.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayData(List<User> userList) {
        showListOfUsers();
        CustomAdapter customAdapter = new CustomAdapter(this, userList);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void displayNoData() {
        showEmptyListOrErrorMessage();
        textViewLoadingDataMessage.setVisibility(View.VISIBLE);
        textViewShowEmptyListOrErrorMessage.setText(R.string.empty_list_network_call);
    }

    @Override
    public void displayError(String error) {
        showListOfUsers();
        textViewShowEmptyListOrErrorMessage.setText(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityPresenter.unSubscribe();
    }

}