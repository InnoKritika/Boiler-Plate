package com.example.boilerplate.ModelClasses;

public class Crud {
    String userId;
    String name;
    String subject;
    String teacherName;

    public Crud(String userId, String name, String subject, String teacherName) {
        this.userId = userId;
        this.name = name;
        this.subject = subject;
        this.teacherName = teacherName;
    }
    public Crud(){

    }

    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
