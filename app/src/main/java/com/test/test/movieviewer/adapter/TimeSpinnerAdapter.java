package com.test.test.movieviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.test.movieviewer.R;
import com.test.test.movieviewer.model.TimeItemModel;
import com.test.test.movieviewer.model.TimeListModel;

import java.util.ArrayList;

public class TimeSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<TimeListModel> cinemaScheds;
    ArrayList<TimeItemModel> cinemaTimes;
    LayoutInflater inflater;

    public TimeSpinnerAdapter(Context applicationContext, ArrayList<TimeListModel> cinemaScheds) {
        this.context = applicationContext;
        this.cinemaScheds = cinemaScheds;
        if (this.cinemaScheds != null) {
            cinemaTimes = cinemaScheds.get(0).getTimes();
        }
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cinemaTimes.size();
    }

    @Override
    public Object getItem(int i) {
        return cinemaTimes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void changeCinemaSched (String parentId) {
        cinemaTimes = new ArrayList<>();
        for (TimeListModel item : cinemaScheds){
            if (item.getParentId().equals(parentId)){
                cinemaTimes = item.getTimes();
            }
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(cinemaTimes.get(i).getLabel());
        return view;
    }
}