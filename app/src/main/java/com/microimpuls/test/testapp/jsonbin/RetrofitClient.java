package com.microimpuls.test.testapp.jsonbin;

import com.microimpuls.test.testapp.BuildConfig;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RetrofitClient {
    private final static RetrofitClient retrofitClient = new RetrofitClient();
    private static Api api;

    public static RetrofitClient getInstance() {
        return retrofitClient;
    }

    public Api getApi() {
        return api;
    }

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.jsonbin.io/b/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        api = retrofit.create(Api.class);
    }
}
