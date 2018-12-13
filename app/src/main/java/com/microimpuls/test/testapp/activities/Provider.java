package com.microimpuls.test.testapp.activities;

import com.microimpuls.test.testapp.UserInfo;

import io.reactivex.subjects.BehaviorSubject;

public class Provider {
    public static BehaviorSubject<UserInfo> usersSubject = BehaviorSubject.create();

    public void setData(UserInfo userInfo) {
        usersSubject.onNext(userInfo);
    }
}
