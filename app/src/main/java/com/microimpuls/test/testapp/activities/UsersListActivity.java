package com.microimpuls.test.testapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.microimpuls.test.testapp.BasicUserInfo;
import com.microimpuls.test.testapp.R;
import com.microimpuls.test.testapp.UserInfo;
import com.microimpuls.test.testapp.jsonbin.Jsonbin;
import com.microimpuls.test.testapp.jsonbin.json_models.Users;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class UsersListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d(this.getLocalClassName(), "OnCreated");

        Jsonbin.usersSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Users>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Users users) {
                        Log.d("MyData", users.getUsers().get(0).getFirstName());
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
}
