package com.microimpuls.test.testapp.jsonbin.json_models;

public class Skill {
    private int skillLevelAssessment;
    private String skillName;

    public Skill() {
    }

    public Skill(int skillLevelAssessment, String skillName) {
        this.skillLevelAssessment = skillLevelAssessment;
        this.skillName = skillName;
    }

    public int getSkillLevelAssessment() {
        return skillLevelAssessment;
    }

    public String getSkillName() {
        return skillName;
    }
}