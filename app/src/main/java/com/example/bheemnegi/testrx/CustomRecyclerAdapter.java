package com.example.bheemnegi.testrx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.UserHolder> {

    Context context;
    List<User> users = new ArrayList<>();

    public CustomRecyclerAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(context).inflate(R.layout.users_items, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int position) {
        userHolder.textViewID.setText(String.valueOf(users.get(position).getId()));
        userHolder.textViewName.setText(users.get(position).getName());
        userHolder.textViewUserName.setText(users.get(position).getUsername());
        userHolder.textViewEmail.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        TextView textViewID, textViewName, textViewUserName, textViewEmail;

        public UserHolder(View view) {
            super(view);
            textViewID = view.findViewById(R.id.tv_user_id);
            textViewName = view.findViewById(R.id.tv_user_name);
            textViewUserName = view.findViewById(R.id.tv_username);
            textViewEmail = view.findViewById(R.id.tv_user_email);
        }
    }

}
