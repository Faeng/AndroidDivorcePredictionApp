package com.example.androidpredictionapp;

import java.sql.Date;
import java.util.concurrent.Semaphore;

public class Prediction {
    private String classNo;
    private String classYes;
    private String dateTime;
    private String email;
    public Prediction() {
        this.dateTime = dateTime;
        this.email = email;
    }

    public String  getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getClassNo() {
        return classNo;
    }
    public String getClassYes() {
        return classYes;
    }
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    public void setClassYes(String classYes) {
        this.classYes = classYes;
    }
}
