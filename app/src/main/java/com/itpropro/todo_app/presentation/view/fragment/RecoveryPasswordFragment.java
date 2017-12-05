package com.itpropro.todo_app.presentation.view.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.itpropro.todo_app.R;
import com.itpropro.todo_app.helpers.Utilities;
import com.itpropro.todo_app.presentation.presenter.RecoveryPassContract;
import com.itpropro.todo_app.presentation.presenter.RecoveryPassPresenter;
import com.itpropro.todo_app.presentation.view.activity.AuthActivity;


public class RecoveryPasswordFragment extends DialogFragment implements RecoveryPassContract.View,View.OnClickListener {

    private RecoveryPassContract.UserActionsListener mActionsListener;
    private EditText eTEmail;
    private TextView tvRecover;

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

        mActionsListener = new RecoveryPassPresenter(this);

        eTEmail = view.findViewById(R.id.etEmail);
        tvRecover = view.findViewById(R.id.tvrecover);

        tvRecover.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tvrecover:
                onRecovery();
                break;
        }
    }

    private void onRecovery(){
        try {
            boolean result = true;
            String email = eTEmail.getText().toString();

            //Si las validaciones no generaron errores
            if (email.toString().trim().length() <= 0) {
                eTEmail.setError("Requerido");
                result = false;
            }
            else{
                eTEmail.setError(null);
            }

            if(result){
                mActionsListener.onRecovery(email);
                goToMainActivity();
            }

        }catch(Exception e){

        }
    }

    @Override
    public void goToLoginFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(LoginFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {

    }

    @Override
    public void showErrorMessage(Exception error) {
        Snackbar.make(getView(),error.getMessage(),Snackbar.LENGTH_LONG).show();
    }
}
