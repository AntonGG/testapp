package com.microimpuls.test.testapp.jsonbin;

import com.microimpuls.test.testapp.jsonbin.json_models.Users;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class Jsonbin {
    private static Api api = RetrofitClient.getInstance().getApi();
    public static BehaviorSubject<Users> usersSubject = BehaviorSubject.create();

    public static void loadData() {
        api.getUserList().subscribe(new Observer<Users>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Users users) {
                usersSubject.onNext(users);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
