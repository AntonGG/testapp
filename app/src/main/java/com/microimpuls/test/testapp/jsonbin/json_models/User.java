package com.microimpuls.test.testapp.jsonbin.json_models;

import java.util.List;

public class User {

    private List<String> hobbies = null;
    private String phoneNumber;
    private String email;
    private List<Skill> skills = null;
    private Integer age;
    private String lastName;
    private String firstName;
    private Integer id;


    public User() {
    }

    public User(List<String> hobbies, String phoneNumber, String email, List<Skill> skills, Integer age, String lastName, String firstName, Integer id) {
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

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}