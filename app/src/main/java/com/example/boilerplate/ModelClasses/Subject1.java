package com.example.boilerplate.ModelClasses;

import java.util.ArrayList;
import java.util.HashMap;

public class Subject1 {
    String userId;
    String name;
    HashMap<String,Object> subjects;

    public Subject1(String userId, String name, HashMap<String,Object> subjects) {
        this.userId = userId;
        this.name = name;
        this.subjects = subjects;
    }
    public Subject1(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String,Object> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<String,Object> list) {
        this.subjects = subjects;
    }
}
