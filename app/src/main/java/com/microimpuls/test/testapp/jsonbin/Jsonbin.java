package com.microimpuls.test.testapp.jsonbin;

import android.util.Log;

import com.microimpuls.test.testapp.App;
import com.microimpuls.test.testapp.Skill;
import com.microimpuls.test.testapp.UserInfo;
import com.microimpuls.test.testapp.database.Database;
import com.microimpuls.test.testapp.jsonbin.json_models.User;
import com.microimpuls.test.testapp.jsonbin.json_models.Users;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class Jsonbin {
    public static BehaviorSubject<List<UserInfo>> usersSubject = BehaviorSubject.create();
    private static Api api = RetrofitClient.getInstance().getApi();
    private static Database database = new Database(App.getAppContext());

    public static void loadDataFromDb() {
        usersSubject.onNext(database.getUsersList());
    }

    public static void loadData() {
        api.getUserList().retry(3).subscribe(new Observer<Users>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Users users) {
                List<UserInfo> userInfoList = new ArrayList<>();
                for (User user : users.getUsers()) {
                    List<Skill> skills = new ArrayList<>();
                    for (com.microimpuls.test.testapp.jsonbin.json_models.Skill skill : user.getSkills()) {
                        skills.add(new Skill(skill.getSkillLevelAssessment(), skill.getSkillName()));
                    }
                    userInfoList.add(new UserInfo(user.getHobbies(),
                            user.getPhoneNumber(),
                            user.getEmail(),
                            skills,
                            user.getAge(),
                            user.getLastName(),
                            user.getFirstName(),
                            user.getId()));
                }
                database.addUsers(userInfoList);
                usersSubject.onNext(userInfoList);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RETROFIT", e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
