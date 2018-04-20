package com.test.test.movieviewer.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.test.movieviewer.R;
import com.test.test.movieviewer.model.MovieResponseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieFragment extends android.support.v4.app.Fragment {

    private View mFragmentView;
    ImageView mLandscapePoster;
    ImageView mPoster;
    TextView mMovieName;
    TextView mGenre;
    TextView mAdvisoryRating;
    TextView mDuration;
    TextView mReleaseDate;
    TextView mSynopsis;
    TextView mCast;

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
        mCast = (TextView) mFragmentView.findViewById(R.id.cast);

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
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOut = new SimpleDateFormat("MMMM dd, yyyy");
        try {
            Date date = sdfIn.parse(data.getReleaseDate());
            mReleaseDate.setText(sdfOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mSynopsis.setText(data.getSynopsis());
        String cast = TextUtils.join(", ",data.getCast());
        mCast.setText(cast);
    }
}
