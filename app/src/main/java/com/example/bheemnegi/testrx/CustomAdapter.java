package com.example.bheemnegi.testrx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        UserHolder userHolder;
        if (view == null) {

            view = layoutInflater.inflate(R.layout.users_items, viewGroup, false);
            userHolder = new UserHolder(view);

            userHolder.textViewID = view.findViewById(R.id.tv_user_id);
            userHolder.textViewName = view.findViewById(R.id.tv_user_name);
            userHolder.textViewUserName = view.findViewById(R.id.tv_username);
            userHolder.textViewEmail = view.findViewById(R.id.tv_user_email);
            view.setTag(userHolder);

        } else {
            userHolder = (UserHolder) view.getTag();
        }
        userHolder.textViewID.setText(String.valueOf(userList.get(i).getId()));
        userHolder.textViewName.setText(userList.get(i).getName());
        userHolder.textViewUserName.setText(userList.get(i).getUsername());
        userHolder.textViewEmail.setText(userList.get(i).getEmail());

        return view;
    }


    class UserHolder {
        TextView textViewID;
        TextView textViewName;
        TextView textViewUserName;
        TextView textViewEmail;

        public UserHolder(View view) {

        }
    }

}
