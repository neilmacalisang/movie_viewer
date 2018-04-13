package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TimeListModel {
    @SerializedName("parent")
    private String parentId;
    @SerializedName("times")
    private ArrayList<TimeItemModel> times;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ArrayList<TimeItemModel> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<TimeItemModel> times) {
        this.times = times;
    }
}
