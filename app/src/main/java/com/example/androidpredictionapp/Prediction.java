package com.example.androidpredictionapp;

import java.sql.Date;
import java.util.concurrent.Semaphore;

public class Prediction {
    private String result;
    private String dateTime;
    private String email;
    public Prediction() {
        this.result = result;
        this.dateTime = dateTime;
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public String  getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
