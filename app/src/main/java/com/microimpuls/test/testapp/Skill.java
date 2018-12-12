package com.microimpuls.test.testapp;

public class Skill {
    private Integer skillLevelAssessment;
    private String skillName;

    public Skill() {
    }

    public Skill(Integer skillLevelAssessment, String skillName) {
        this.skillLevelAssessment = skillLevelAssessment;
        this.skillName = skillName;
    }

    public Integer getSkillLevelAssessment() {
        return skillLevelAssessment;
    }

    public void setSkillLevelAssessment(Integer skillLevelAssessment) {
        this.skillLevelAssessment = skillLevelAssessment;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

}