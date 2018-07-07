package com.example.bheemnegi.testrx;

import java.util.List;

public interface OnDataListener {

    public void onSuccess(List<User> userList);
    public void onFailure(String error);

}
