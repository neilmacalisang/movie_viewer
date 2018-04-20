package com.test.test.movieviewer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.test.movieviewer.R;
import com.test.test.movieviewer.adapter.CinemaSpinnerAdapter;
import com.test.test.movieviewer.adapter.ScheduleSpinnerAdapter;
import com.test.test.movieviewer.adapter.TimeSpinnerAdapter;
import com.test.test.movieviewer.api.ApiClient;
import com.test.test.movieviewer.api.MovieViewerApi;
import com.test.test.movieviewer.helpers.ProgressDialogUtils;
import com.test.test.movieviewer.model.CinemaItemModel;
import com.test.test.movieviewer.model.MovieResponseModel;
import com.test.test.movieviewer.model.ScheduleResponseModel;
import com.test.test.movieviewer.model.SeatmapResponseModel;
import com.test.test.movieviewer.model.TimeItemModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatmapFragment extends android.support.v4.app.Fragment {

    private View mFragmentView;
    TextView mTheater;
    Spinner mDateSpinner;
    Spinner mCinemaSpinner;
    Spinner mTimeSpinner;

    MovieResponseModel movieData;
    ScheduleResponseModel scheduleData;
    TimeItemModel timeDetails;

    CinemaSpinnerAdapter mCinemaSpinnerAdapter;
    TimeSpinnerAdapter mTimeSpinnerAdapter;

    ArrayList<String> selectedSeats;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_seatmap, container, false);

        Bundle arguments = getArguments();

        String dataJson = arguments.getString("movieJson");
        movieData = new Gson().fromJson(dataJson, MovieResponseModel.class);

        dataJson = arguments.getString("scheduleJson");
        scheduleData = new Gson().fromJson(dataJson, ScheduleResponseModel.class);

        mCinemaSpinner = (Spinner) mFragmentView.findViewById(R.id.cinema_spinner);
        mDateSpinner = (Spinner) mFragmentView.findViewById(R.id.date_spinner);
        mTimeSpinner = (Spinner) mFragmentView.findViewById(R.id.time_spinner);

        setupDateSpinner();

        mTheater = (TextView) mFragmentView.findViewById(R.id.theater);
        mTheater.setText(movieData.getTheater());

        return mFragmentView;
    }

    private void setupDateSpinner(){
        ScheduleSpinnerAdapter scheduleAdapter = new ScheduleSpinnerAdapter(getActivity(), scheduleData.getDates());
        mDateSpinner.setAdapter(scheduleAdapter);

        mDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupCinemaSpinner(scheduleData.getDates().get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupCinemaSpinner(String dateParentId) {
        if (null == mCinemaSpinnerAdapter) {
            mCinemaSpinnerAdapter = new CinemaSpinnerAdapter(getActivity(), scheduleData.getCinemas());
        }
        mCinemaSpinnerAdapter.changeCinemaSched(dateParentId);
        mCinemaSpinner.setAdapter(mCinemaSpinnerAdapter);
        if (mCinemaSpinnerAdapter.getCount() > 0) {

            mCinemaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CinemaItemModel cinema = (CinemaItemModel) mCinemaSpinnerAdapter.getItem(position);
                    setupTimeSpinner(cinema.getId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            setupTimeSpinner("");
        }
    }

    private void setupTimeSpinner(final String timeId) {
        if (null == mTimeSpinnerAdapter) {
            mTimeSpinnerAdapter = new TimeSpinnerAdapter(getActivity(), scheduleData.getTimes());
        }
        mTimeSpinnerAdapter.changeCinemaSched(timeId);
        mTimeSpinner.setAdapter(mTimeSpinnerAdapter);
        if (mTimeSpinner.getCount() > 0) {
            mTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    timeDetails = (TimeItemModel) mTimeSpinnerAdapter.getItem(position);
                    callSeatmapJson(0);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            selectedSeats.clear();
            LinearLayout seatmapLayout = mFragmentView.findViewById(R.id.zoomable_layout);
            seatmapLayout.removeAllViews();
            selectSeats();
        }
    }

    private void callSeatmapJson(final int retryCount) {
        MovieViewerApi apiService = ApiClient.getClient("").create(MovieViewerApi.class);
        Call<SeatmapResponseModel> call = apiService.getSeatmapJson();
        ProgressDialogUtils.showDialog("Loading", getActivity());
        call.enqueue(new Callback<SeatmapResponseModel>() {
            @Override
            public void onResponse(Call<SeatmapResponseModel> call, Response<SeatmapResponseModel> response) {
                if(response.isSuccessful()){
                    setupSeatmap(response.body());
                } else {
                    callSeatmapJson(retryCount + 1);
                }
                ProgressDialogUtils.dismissDialog();
            }

            @Override
            public void onFailure(Call<SeatmapResponseModel> call, Throwable t) {
                callSeatmapJson( retryCount + 1);
            }
        });
    }

    private void setupSeatmap(SeatmapResponseModel seatmapData){
        selectedSeats = new ArrayList<String>();
        selectSeats();
        LinearLayout seatmapLayout = mFragmentView.findViewById(R.id.zoomable_layout);
        seatmapLayout.removeAllViews();
        View movieScreen = getLayoutInflater().inflate(R.layout.item_movie_screen,null);
        seatmapLayout.addView(movieScreen);
        for (ArrayList<String> row : seatmapData.getSeatmap()) {
            LinearLayout rowLayout = new LinearLayout(getActivity());

            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            View rowText = getLayoutInflater().inflate(R.layout.item_text,null);

            TextView rowLetter = (TextView) rowText.findViewById(R.id.textView);
            rowLetter.setText(row.get(0).substring(0,1));
            rowLayout.addView(rowText);
            for(final String seat : row) {
                final View seatView = getLayoutInflater().inflate(R.layout.item_seat, null);
                final View seatColor = seatView.findViewById(R.id.seat);
                if (seatmapData.getAvailable().getSeats().contains(seat)) {
                    seatColor.setBackgroundColor(getActivity().getResources().getColor(android.R.color.darker_gray));
                    seatView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seatView.setSelected(!seatView.isSelected());
                            if (seatView.isSelected()) {
                                if (selectedSeats.size() < 10) {
                                    selectedSeats.add(seat);
                                    seatColor.setBackgroundColor(getActivity().getResources().getColor(android.R.color.holo_red_dark));
                                }
                            } else {
                                selectedSeats.remove(seat);
                                seatColor.setBackgroundColor(getActivity().getResources().getColor(android.R.color.darker_gray));
                            }
                            selectSeats();
                        }
                    });
                } else if (seat.contains("(")) {
                    seatColor.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
                }else {
                    seatColor.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                }
                rowLayout.addView(seatView);
            }
            rowText = getLayoutInflater().inflate(R.layout.item_text,null);
            rowLetter = (TextView) rowText.findViewById(R.id.textView);
            rowLetter.setText(row.get(0).substring(0,1));
            rowLayout.addView(rowText);

            seatmapLayout.addView(rowLayout);
        }
    }

    private void selectSeats() {
        GridLayout seatDisplay = (GridLayout) mFragmentView.findViewById(R.id.seat_display);
        seatDisplay.removeAllViews();
        for (String seat : selectedSeats){
            final View seatView = getLayoutInflater().inflate(R.layout.item_selected_seat, null);
            TextView seatName = seatView.findViewById(R.id.seatText);
            seatName.setText(seat);
            seatDisplay.addView(seatView);
        }
        TextView price = (TextView) mFragmentView.findViewById(R.id.price_text);
        double amount = Double.parseDouble(timeDetails.getPrice());
        amount = amount * selectedSeats.size();
        DecimalFormat formatter = new DecimalFormat("PHP #,###.##");
        price.setText(formatter.format(amount));
    }
}
