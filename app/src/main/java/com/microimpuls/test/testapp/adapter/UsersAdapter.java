package com.microimpuls.test.testapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.microimpuls.test.testapp.R;
import com.microimpuls.test.testapp.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private List<UserInfo> userInfoList = new ArrayList<>();

    public void setItem(List<UserInfo> userInfoList) {
        this.userInfoList.clear();
        this.userInfoList.addAll(userInfoList);
        notifyDataSetChanged();
    }

    public UserInfo getItem(int position) {
        return userInfoList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(userInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        UsersAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(UserInfo userInfo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textId;
        private TextView textUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.text_id);
            textUsername = itemView.findViewById(R.id.text_username);
            itemView.setOnClickListener(this);
        }

        public void bind(UserInfo userInfo) {
            textId.setText(Integer.toString(userInfo.getId()));
            textUsername.setText(userInfo.getFirstName());
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getItem(getAdapterPosition()));
        }
    }
}