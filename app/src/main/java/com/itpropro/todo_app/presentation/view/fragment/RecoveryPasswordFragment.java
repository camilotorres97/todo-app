package com.itpropro.todo_app.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itpropro.todo_app.R;


public class RecoveryPasswordFragment extends DialogFragment {


    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }

    public static RecoveryPasswordFragment getInstance(){
        return new RecoveryPasswordFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_recovery, container, false);
        return view;
    }

}
