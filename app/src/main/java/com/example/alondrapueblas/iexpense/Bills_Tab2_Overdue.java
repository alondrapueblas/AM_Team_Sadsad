package com.example.alondrapueblas.iexpense;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alondra Pueblas on 14/05/2018.
 */

public class Bills_Tab2_Overdue extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_overdue, container, false);
        return rootView;
    }
}
