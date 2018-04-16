package com.test.test.movieviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.test.test.movieviewer.R;
import com.test.test.movieviewer.model.CinemaItemModel;
import com.test.test.movieviewer.model.CinemaListModel;

import java.util.ArrayList;

public class CinemaSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<CinemaListModel> cinemaScheds;
    ArrayList<CinemaItemModel> cinemas;
    LayoutInflater inflater;

    public CinemaSpinnerAdapter(Context applicationContext, ArrayList<CinemaListModel> cinemaScheds) {
        this.context = applicationContext;
        this.cinemaScheds = cinemaScheds;
        if (this.cinemaScheds != null) {
            cinemas = cinemaScheds.get(0).getCinemas();
        }
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cinemas.size();
    }

    @Override
    public Object getItem(int i) {
        return cinemas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void changeCinemaSched (String parentId) {
        cinemas = new ArrayList<>();
        for (CinemaListModel item : cinemaScheds){
            if (item.getParentId().equals(parentId)){
                cinemas = item.getCinemas();
            }
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(cinemas.get(i).getLabel());
        return view;
    }
}