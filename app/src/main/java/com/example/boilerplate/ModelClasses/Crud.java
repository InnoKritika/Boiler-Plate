package com.example.boilerplate.ModelClasses;

public class Crud {
    String userId;
    String name;
    String subject;

    public Crud(String userId, String name, String subject) {
        this.userId = userId;
        this.name = name;
        this.subject = subject;
    }
    public Crud(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
