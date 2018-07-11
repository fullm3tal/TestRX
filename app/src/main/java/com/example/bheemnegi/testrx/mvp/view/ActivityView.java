package com.example.bheemnegi.testrx.mvp.view;

import android.widget.ListView;

import com.example.bheemnegi.testrx.User;

import java.util.List;

public interface ActivityView {

    void displayData(List<User> userList);

    void displayNoData();

    void displayError(String error);

    void displayNamesOnly(List<String> namesList);
}
