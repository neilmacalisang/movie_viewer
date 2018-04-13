package com.test.test.movieviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponseModel {
    @SerializedName("movie_id")
    private String dates;
    @SerializedName("advisory_rating")
    private String advisoryRating;
    @SerializedName("canonical_title")
    private String canonicalTitle;
    @SerializedName("cast")
    private ArrayList<String> cast;
    @SerializedName("genre")
    private String genre;
    @SerializedName("has_schedules")
    private int hasSchedules;
    @SerializedName("is_inactive")
    private int isInactive;
    @SerializedName("is showing")
    private int isShowing;
    @SerializedName("link_name")
    private String linkName;
    @SerializedName("poster")
    private String poster;
    @SerializedName("poster_landscape")
    private String posterLandscape;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("runtime_mins")
    private float runtimeMins;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("trailer")
    private String trailer;
    @SerializedName("average_rating")
    private String averageRating;
    @SerializedName("total_reviews")
    private String totalReviews;
    @SerializedName("variants")
    private ArrayList<String> variants;
    @SerializedName("theater")
    private String theater;
    @SerializedName("order")
    private int order;
    @SerializedName("is_featured")
    private int isFeatured;
    @SerializedName("watch_list")
    private boolean watchList;
    @SerializedName("your_rating")
    private int yourRating;

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getAdvisoryRating() {
        return advisoryRating;
    }

    public void setAdvisoryRating(String advisoryRating) {
        this.advisoryRating = advisoryRating;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getHasSchedules() {
        return hasSchedules;
    }

    public void setHasSchedules(int hasSchedules) {
        this.hasSchedules = hasSchedules;
    }

    public int getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(int isInactive) {
        this.isInactive = isInactive;
    }

    public int getIsShowing() {
        return isShowing;
    }

    public void setIsShowing(int isShowing) {
        this.isShowing = isShowing;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPosterLandscape() {
        return posterLandscape;
    }

    public void setPosterLandscape(String posterLandscape) {
        this.posterLandscape = posterLandscape;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRuntimeMins() {
        return runtimeMins;
    }

    public void setRuntimeMins(float runtimeMins) {
        this.runtimeMins = runtimeMins;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(String totalReviews) {
        this.totalReviews = totalReviews;
    }

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<String> variants) {
        this.variants = variants;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public boolean isWatchList() {
        return watchList;
    }

    public void setWatchList(boolean watchList) {
        this.watchList = watchList;
    }

    public int getYourRating() {
        return yourRating;
    }

    public void setYourRating(int yourRating) {
        this.yourRating = yourRating;
    }

    public String getDurationString() {
        String duration = "";
        int hours = (int)(runtimeMins / 60);
        int mins = (int)(runtimeMins % 60);
        duration = hours + "hrs " + mins + "mins";
        return duration;
    }
}
