package com.xuanduy.terrascope.dto.persona.respond;

import java.util.List;

public class PersonasDTO {
    int id;
    String name;
    String age;
    String gender;
    String location;
    List<String> painPoints;
    List<String> needsAndGoals;
    List<String> Background;
    List<String> SkillAndExperiences;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getBackground() {
        return Background;
    }

    public void setBackground(List<String> background) {
        Background = background;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNeedsAndGoals() {
        return needsAndGoals;
    }

    public void setNeedsAndGoals(List<String> needsAndGoals) {
        this.needsAndGoals = needsAndGoals;
    }

    public List<String> getPainPoints() {
        return painPoints;
    }

    public void setPainPoints(List<String> painPoints) {
        this.painPoints = painPoints;
    }

    public List<String> getSkillAndExperiences() {
        return SkillAndExperiences;
    }

    public void setSkillAndExperiences(List<String> skillAndExperiences) {
        SkillAndExperiences = skillAndExperiences;
    }
}
