package com.test.test.movieviewer.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.test.movieviewer.R;
import com.test.test.movieviewer.api.MovieViewerApi;
import com.test.test.movieviewer.model.MovieResponseModel;
import com.test.test.movieviewer.model.ScheduleResponseModel;
import com.test.test.movieviewer.model.SeatmapResponseModel;

public class SeatmapFragment extends android.support.v4.app.Fragment {

    private View mFragmentView;
    TextView mTheater;

    MovieResponseModel movieData;
    SeatmapResponseModel seatmapData;
    ScheduleResponseModel scheduleData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_seatmap, container, false);

        Bundle arguments = getArguments();
        String dataJson = arguments.getString("movieJson");
        Log.d("movie", dataJson);
        movieData = new Gson().fromJson(dataJson, MovieResponseModel.class);
        dataJson = arguments.getString("seatmapJson");
        Log.d("seatmap", dataJson);
        seatmapData = new Gson().fromJson(dataJson, SeatmapResponseModel.class);
        dataJson = arguments.getString("scheduleJson");
        Log.d("sched", dataJson);
        scheduleData = new Gson().fromJson(dataJson, ScheduleResponseModel.class);

        mTheater = (TextView) mFragmentView.findViewById(R.id.theater);
        mTheater.setText(movieData.getTheater());

        return mFragmentView;
    }
}
