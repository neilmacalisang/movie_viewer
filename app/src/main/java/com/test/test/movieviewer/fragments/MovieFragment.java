package com.test.test.movieviewer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.test.movieviewer.R;
import com.test.test.movieviewer.api.ApiClient;
import com.test.test.movieviewer.api.MovieViewerApi;
import com.test.test.movieviewer.model.MovieResponseModel;
import com.test.test.movieviewer.model.ScheduleResponseModel;
import com.test.test.movieviewer.model.SeatmapResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends android.support.v4.app.Fragment {

    private View mFragmentView;
    MovieViewerApi apiService;
    ImageView mLandscapePoster;
    ImageView mPoster;
    TextView mMovieName;
    TextView mGenre;
    TextView mAdvisoryRating;
    TextView mDuration;
    TextView mReleaseDate;
    TextView mSynopsis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_movie, container, false);

        mLandscapePoster = (ImageView) mFragmentView.findViewById(R.id.landscape_poster);
        mPoster = (ImageView) mFragmentView.findViewById(R.id.poster);
        mMovieName = (TextView) mFragmentView.findViewById(R.id.name);
        mGenre = (TextView) mFragmentView.findViewById(R.id.genre);
        mAdvisoryRating = (TextView) mFragmentView.findViewById(R.id.advisory_rating);
        mDuration = (TextView) mFragmentView.findViewById(R.id.duration);
        mReleaseDate = (TextView) mFragmentView.findViewById(R.id.release_date);
        mSynopsis = (TextView) mFragmentView.findViewById(R.id.synopsis);

        Bundle arguments = getArguments();
        String movieJson = arguments.getString("movieJson");
        updateLayout(new Gson().fromJson(movieJson, MovieResponseModel.class));

        return mFragmentView;
    }

    public void updateLayout(MovieResponseModel data) {
        Picasso.get().load(data.getPosterLandscape())
                .fit()
                .centerInside()
                .placeholder(R.mipmap.ic_launcher)
                .into(mLandscapePoster);
        Picasso.get().load(data.getPoster())
                .fit()
                .centerInside()
                .placeholder(R.mipmap.ic_launcher)
                .into(mPoster);

        mMovieName.setText(data.getCanonicalTitle());
        mGenre.setText(data.getGenre());
        mAdvisoryRating.setText(data.getAdvisoryRating());
        mDuration.setText(data.getDurationString());
        mReleaseDate.setText(data.getReleaseDate());
        mSynopsis.setText(data.getSynopsis());
    }
}
