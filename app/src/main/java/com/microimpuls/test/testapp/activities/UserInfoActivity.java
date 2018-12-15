package com.microimpuls.test.testapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.microimpuls.test.testapp.R;
import com.microimpuls.test.testapp.Skill;
import com.microimpuls.test.testapp.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class UserInfoActivity extends AppCompatActivity {

    private Context context;
    private TextView textId;
    private TextView textFirstName;
    private TextView textLastName;
    private TextView textAge;
    private TextView textPhone;
    private TextView textEmail;
    private ListView listViewSkills;
    private ListView listViewHobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        context = this;
        init();

        Provider.usersSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        textId.setText("ID: " + userInfo.getId());
                        textFirstName.setText("First name: " + userInfo.getFirstName());
                        textLastName.setText("Last name: " + userInfo.getLastName());
                        textAge.setText("Age: " + userInfo.getAge());
                        textPhone.setText("Phone: " + userInfo.getPhoneNumber());
                        textEmail.setText("Email: " + userInfo.getEmail());

                        List<Map<String, String>> skillList = new ArrayList<>();
                        for (Skill skill : userInfo.getSkills()) {
                            Map<String, String> map = new HashMap<>();
                            map.put("skillName", skill.getSkillName());
                            map.put("skillLevelAssessment",
                                    Integer.toString(skill.getSkillLevelAssessment()));
                            skillList.add(map);
                        }

                        SimpleAdapter skillSampleAdapter = new SimpleAdapter(context, skillList, android.R.layout.simple_list_item_2,
                                new String[]{"skillName", "skillLevelAssessment"},
                                new int[]{android.R.id.text1, android.R.id.text2});

                        ArrayAdapter<String> hobbiesAdapter = new ArrayAdapter<>(context,
                                android.R.layout.simple_list_item_1, userInfo.getHobbies());

                        listViewSkills.setAdapter(skillSampleAdapter);
                        listViewHobbies.setAdapter(hobbiesAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void init() {
        textId = findViewById(R.id.text_id);
        textFirstName = findViewById(R.id.text_first_name);
        textLastName = findViewById(R.id.text_last_name);
        textAge = findViewById(R.id.text_age);
        textPhone = findViewById(R.id.text_phone);
        textEmail = findViewById(R.id.text_email);
        listViewSkills = findViewById(R.id.listView_skills);
        listViewHobbies = findViewById(R.id.listView_hobbies);
    }
}
