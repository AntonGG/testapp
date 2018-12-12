package com.microimpuls.test.testapp.jsonbin;

import com.microimpuls.test.testapp.jsonbin.json_models.Users;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("5c0facf5ec62650f24dd2819")
    Observable<Users> getUserList();
}
