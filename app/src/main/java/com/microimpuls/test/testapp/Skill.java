package com.microimpuls.test.testapp;

public class Skill {
    private int skillLevelAssessment;
    private String skillName;

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