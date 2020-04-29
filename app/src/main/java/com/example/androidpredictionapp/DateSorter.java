package com.example.androidpredictionapp;

import java.util.Comparator;

public class DateSorter implements Comparator<Prediction> {
    @Override
    public int compare(Prediction o1, Prediction o2) {
        return o2.getDateTime().compareToIgnoreCase(o1.getDateTime());
    }
}
