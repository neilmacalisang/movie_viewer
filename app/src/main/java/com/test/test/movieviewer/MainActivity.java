package com.test.test.movieviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.test.movieviewer.api.ApiClient;
import com.test.test.movieviewer.api.MovieViewerApi;
import com.test.test.movieviewer.model.MovieResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView mLandscapePoster;
    ImageView mPoster;
    TextView mMovieName;
    TextView mGenre;
    TextView mAdvisoryRating;
    TextView mDuration;
    TextView mReleaseDate;
    TextView mSynopsis;
    View mViewSeatMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLandscapePoster = (ImageView) findViewById(R.id.landscape_poster);
        mPoster = (ImageView) findViewById(R.id.poster);
        mMovieName = (TextView) findViewById(R.id.name);
        mGenre = (TextView) findViewById(R.id.genre);
        mAdvisoryRating = (TextView) findViewById(R.id.advisory_rating);
        mDuration = (TextView) findViewById(R.id.duration);
        mReleaseDate = (TextView) findViewById(R.id.release_date);
        mSynopsis = (TextView) findViewById(R.id.synopsis);
        mViewSeatMap = findViewById(R.id.seat_map);

        callMovieJson();
    }

    private void callMovieJson() {
        MovieViewerApi apiService = ApiClient.getClient("").create(MovieViewerApi.class);

        Call<MovieResponseModel> call = apiService.getMovieJson();
        call.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
//                Log.e("TEST", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    MovieResponseModel model = response.body();
                    Picasso.get().load(model.getPosterLandscape())
                            .fit()
                            .centerInside()
                            .placeholder(R.mipmap.ic_launcher)
                            .into(mLandscapePoster);
                    Picasso.get().load(model.getPoster())
                            .fit()
                            .centerInside()
                            .placeholder(R.mipmap.ic_launcher)
                            .into(mPoster);

                    mMovieName.setText(model.getCanonicalTitle());
                    mGenre.setText(model.getGenre());
                    mAdvisoryRating.setText(model.getAdvisoryRating());
                    mDuration.setText(model.getDurationString());
                    mReleaseDate.setText(model.getReleaseDate());
                    mSynopsis.setText(model.getSynopsis());

                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                
            }
        });
    }
}
