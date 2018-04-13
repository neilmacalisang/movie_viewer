package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

public class TimeItemModel {
    @SerializedName("id")
    private String id;
    @SerializedName("label")
    private String label;
    @SerializedName("schedule_id")
    private String scheduleId;
    @SerializedName("popcorn_price")
    private String popcornPrice;
    @SerializedName("popcorn_label")
    private String popcornLabel;
    @SerializedName("seating_type")
    private String seatingType;
    @SerializedName("price")
    private String price;
    @SerializedName("variant")
    private String variant;

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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPopcornPrice() {
        return popcornPrice;
    }

    public void setPopcornPrice(String popcornPrice) {
        this.popcornPrice = popcornPrice;
    }

    public String getPopcornLabel() {
        return popcornLabel;
    }

    public void setPopcornLabel(String popcornLabel) {
        this.popcornLabel = popcornLabel;
    }

    public String getSeatingType() {
        return seatingType;
    }

    public void setSeatingType(String seatingType) {
        this.seatingType = seatingType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
