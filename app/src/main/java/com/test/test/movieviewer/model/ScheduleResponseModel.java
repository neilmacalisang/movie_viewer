package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleResponseModel {
    @SerializedName("dates")
    private ArrayList<DateModel> dates;
    @SerializedName("cinemas")
    private ArrayList<CinemaListModel> cinemas;
    @SerializedName("times")
    private ArrayList<TimeListModel> times;

    public ArrayList<DateModel> getDates() {
        return dates;
    }

    public void setDates(ArrayList<DateModel> dates) {
        this.dates = dates;
    }

    public ArrayList<CinemaListModel> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<CinemaListModel> cinemas) {
        this.cinemas = cinemas;
    }

    public ArrayList<TimeListModel> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<TimeListModel> times) {
        this.times = times;
    }
}
