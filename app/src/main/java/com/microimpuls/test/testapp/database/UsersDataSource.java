package com.microimpuls.test.testapp.database;

import android.database.Cursor;

import com.microimpuls.test.testapp.BasicUserInfo;

import java.util.List;

interface UsersDataSource<T extends BasicUserInfo> {

    void addUsers(List<T> users);

	long addUser( long userId, String username, int age );

	Cursor getUsersList();

	T getUserInfo( long id );
}
