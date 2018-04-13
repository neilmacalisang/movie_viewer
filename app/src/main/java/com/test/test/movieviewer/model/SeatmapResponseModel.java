package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SeatmapResponseModel {
    @SerializedName("seatmap")
    private ArrayList<ArrayList<String>> seatmap;
    @SerializedName("seat_count")
    private int seatCount;
    @SerializedName("available")
    private AvailableSeatModel available;

    public ArrayList<ArrayList<String>> getSeatmap() {
        return seatmap;
    }

    public void setSeatmap(ArrayList<ArrayList<String>> seatmap) {
        this.seatmap = seatmap;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public AvailableSeatModel getAvailable() {
        return available;
    }

    public void setAvailable(AvailableSeatModel available) {
        this.available = available;
    }
}
