package com.example.alondrapueblas.iexpense;

/**
 * Created by Alondra Pueblas on 14/05/2018.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Bills_Tab1_Paid extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_paid, container, false);
        return rootView;
    }
}
