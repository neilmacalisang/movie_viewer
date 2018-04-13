package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

public class CinemaItemModel {
    @SerializedName("id")
    private String id;
    @SerializedName("label")
    private String label;
    @SerializedName("cinema_id")
    private String cinemaId;

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

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }
}
