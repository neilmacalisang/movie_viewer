package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CinemaListModel {
    @SerializedName("parent")
    private String parentId;
    @SerializedName("cinemas")
    private ArrayList<CinemaItemModel> cinemas;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ArrayList<CinemaItemModel> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<CinemaItemModel> cinemas) {
        this.cinemas = cinemas;
    }
}
