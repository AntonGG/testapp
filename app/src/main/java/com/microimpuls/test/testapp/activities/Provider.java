package com.microimpuls.test.testapp.activities;

import com.microimpuls.test.testapp.UserInfo;

import io.reactivex.subjects.BehaviorSubject;

public class Provider {
    public final static BehaviorSubject<UserInfo> usersSubject = BehaviorSubject.create();
}
