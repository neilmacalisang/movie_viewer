package com.test.test.movieviewer.api;

import com.test.test.movieviewer.model.MovieResponseModel;
import com.test.test.movieviewer.model.ScheduleResponseModel;
import com.test.test.movieviewer.model.SeatmapResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MovieViewerApi {
    @Headers({
            "Content-type: application/json"
    })


    @GET("seatmap.json")
    Call<SeatmapResponseModel> getSeatmapJson();

    @GET("movie.json")
    Call<MovieResponseModel> getMovieJson();

    @GET("schedule.json")
    Call<ScheduleResponseModel> getScheduleJson();

}
