package com.test.test.movieviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.test.movieviewer.R;
import com.test.test.movieviewer.model.DateModel;

import java.util.ArrayList;

public class ScheduleSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<DateModel> dates;
    LayoutInflater inflater;

    public ScheduleSpinnerAdapter(Context applicationContext, ArrayList<DateModel> dates) {
        this.context = applicationContext;
        this.dates = dates;

        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int i) {
        return dates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(dates.get(i).getLabel());
        return view;
    }
}