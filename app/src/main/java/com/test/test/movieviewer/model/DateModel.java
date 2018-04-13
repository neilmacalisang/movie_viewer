package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

public class DateModel {
    @SerializedName("id")
    private String id;
    @SerializedName("label")
    private String label;
    @SerializedName("date")
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
