package com.microimpuls.test.testapp;

import android.app.Application;
import android.content.Context;

public final class App extends Application {
    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
