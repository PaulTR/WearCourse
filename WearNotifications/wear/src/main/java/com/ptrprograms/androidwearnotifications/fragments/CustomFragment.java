package com.ptrprograms.androidwearnotifications.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ptrprograms.androidwearnotifications.R;

/**
 * Created by Paul on 7/3/15.
 */
public class CustomFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom, container, false);
    }
}
