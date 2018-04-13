package com.test.test.movieviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.test.movieviewer.api.ApiClient;
import com.test.test.movieviewer.api.MovieViewerApi;
import com.test.test.movieviewer.fragments.MovieFragment;
import com.test.test.movieviewer.fragments.SeatmapFragment;
import com.test.test.movieviewer.model.MovieResponseModel;
import com.test.test.movieviewer.model.ScheduleResponseModel;
import com.test.test.movieviewer.model.SeatmapResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MovieViewerApi apiService;

    //Data
    MovieResponseModel movieData;
    ScheduleResponseModel scheduleData;
    SeatmapResponseModel seatMapData;

    FrameLayout mFragmentContainer;
    View mViewSeatmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mViewSeatmap = findViewById(R.id.seat_map);
        mViewSeatmap.setOnClickListener(this);
        apiService = ApiClient.getClient("").create(MovieViewerApi.class);
        callMovieJson(0);

    }

    public void setupView(String movieData) {
        if (mFragmentContainer != null) {

            MovieFragment movieFragment = new MovieFragment();

            Bundle arguments = new Bundle();
            arguments.putString( "movieJson" , movieData);

            movieFragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().add(mFragmentContainer.getId(), movieFragment).commit();
        }

    }

    private void callMovieJson(final int retryCount) {
        if (retryCount < 3) {
            Call<MovieResponseModel> call = apiService.getMovieJson();
            call.enqueue(new Callback<MovieResponseModel>() {
                @Override
                public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                    if (response.isSuccessful()) {
                        setupView(new Gson().toJson(response.body()));
                        movieData = response.body();
                        callScheduleJson(0);
                    } else {
                        callMovieJson(retryCount + 1);
                    }
                }

                @Override
                public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                    callMovieJson(retryCount + 1);
                }
            });
        }
    }

    private void callScheduleJson(final int retryCount) {
        Call<ScheduleResponseModel> call = apiService.getScheduleJson();
        call.enqueue(new Callback<ScheduleResponseModel>() {
            @Override
            public void onResponse(Call<ScheduleResponseModel> call, Response<ScheduleResponseModel> response) {
                if (response.isSuccessful()) {
                    Log.d("Schedule", new Gson().toJson(response.body()));
                    scheduleData = response.body();
                    callSeatmapJson(0);
                } else {
                    callScheduleJson(retryCount + 1);
                }

            }

            @Override
            public void onFailure(Call<ScheduleResponseModel> call, Throwable t) {
                callScheduleJson(retryCount + 1);
            }
        });
    }

    private void callSeatmapJson(final int retryCount) {
        Call<SeatmapResponseModel> call = apiService.getSeatmapJson();
        call.enqueue(new Callback<SeatmapResponseModel>() {
            @Override
            public void onResponse(Call<SeatmapResponseModel> call, Response<SeatmapResponseModel> response) {
                if(response.isSuccessful()){
                    Log.d("Seatmap", new Gson().toJson(response.body()));
                    seatMapData = response.body();
                } else {
                    callSeatmapJson(retryCount + 1);
                }
            }

            @Override
            public void onFailure(Call<SeatmapResponseModel> call, Throwable t) {
                callSeatmapJson( retryCount + 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            mViewSeatmap.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seat_map:
                SeatmapFragment fragment = new SeatmapFragment();
                Bundle arguments = new Bundle();
                arguments.putString("movieJson",new Gson().toJson(movieData));
                arguments.putString("scheduleJson",new Gson().toJson(scheduleData));
                arguments.putString("seatmapJson",new Gson().toJson(seatMapData));
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(mFragmentContainer.getId(), fragment).addToBackStack("seatmapFragment").commit();
                mViewSeatmap.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
