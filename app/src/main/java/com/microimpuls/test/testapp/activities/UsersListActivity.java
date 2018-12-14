package com.microimpuls.test.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.microimpuls.test.testapp.R;
import com.microimpuls.test.testapp.UserInfo;
import com.microimpuls.test.testapp.adapter.UsersAdapter;
import com.microimpuls.test.testapp.jsonbin.Jsonbin;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class UsersListActivity extends AppCompatActivity implements UsersAdapter.ClickListener {
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_view);

        usersAdapter = new UsersAdapter();
        usersAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(usersAdapter);

        Log.d(this.getLocalClassName(), "OnCreated");

        Jsonbin.usersSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserInfo> users) {
                        Log.d("My Debug", "Кол-во пользователей " + users.size());
                        usersAdapter.setItem(users);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loadData(View view) {
        Jsonbin.loadData();
    }

    @Override
    public void onItemClick(UserInfo userInfo) {
        //   Log.d("SSSSSSS", userInfo.getFirstName());
        Provider.usersSubject.onNext(userInfo);
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }
}
