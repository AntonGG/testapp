package com.microimpuls.test.testapp;

import java.util.List;

public class UserInfo extends BasicUserInfo {
    private List<String> hobbies;
    private String phoneNumber;
    private String email;
    private List<Skill> skills;
    private Integer age;
    private String lastName;
    private String firstName;
    private Integer id;

    public UserInfo(List<String> hobbies,
                    String phoneNumber,
                    String email,
                    Integer age, String lastName, String firstName, Integer id) {
        super(id, firstName);
        this.hobbies = hobbies;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.skills = skills;
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Integer getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }
}
