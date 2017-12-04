package com.itpropro.todo_app.presentation.view.fragment;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itpropro.todo_app.R;
import com.itpropro.todo_app.helpers.Utilities;
import com.itpropro.todo_app.presentation.presenter.LoginContract;
import com.itpropro.todo_app.presentation.presenter.SignUpContract;
import com.itpropro.todo_app.presentation.presenter.SignUpPresenter;
import com.itpropro.todo_app.presentation.view.activity.AuthActivity;
import com.itpropro.todo_app.presentation.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpContract.View, View.OnClickListener {

    private SignUpContract.UserActionsListener mActionsListener;
    private TextInputLayout tilFullName;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnRegistry;
    private Button btnAlreadyHaveAccount;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);

        mActionsListener = new SignUpPresenter(this);

        tilFullName = view.findViewById(R.id.tilFullName);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        btnRegistry = view.findViewById(R.id.btnRegistry);
        btnAlreadyHaveAccount = view.findViewById(R.id.btnAlreadyHaveAccount);

        btnRegistry.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistry:
                onSignup();
                break;
            case R.id.btnAlreadyHaveAccount:
                //TODO Fix this event action
                goToLoginFragment();
                break;
        }
    }

    @Override
    public void goToLoginFragment() {
        getFragmentManager().popBackStack(); //popBackStack Muestra el ultimo fragment mostrado
        // AuthActivity authActivity = (AuthActivity) getActivity();
        //authActivity.replaceFragment(LoginFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(Exception error) {
        Snackbar.make(getView(),error.getMessage(),Snackbar.LENGTH_LONG).show();
    }

    private void onSignup(){
        try{
            boolean result = true;
            String fullname = tilFullName.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            if(Utilities.isEmpty(fullname)){
                tilFullName.setError(getString(R.string.is_required));
                tilFullName.setErrorEnabled(true);
                result = false;
            }else{
                tilFullName.setError(null);
                tilFullName.setErrorEnabled(false);
            }

            if(Utilities.isEmpty(email)){
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            }else{
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            if(Utilities.isEmpty(password)){
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true);
                result = false;
            }else{
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if(result){
                mActionsListener.onSignUp(fullname,email,password);
            }

        }catch (Exception e){

        }
    }

}
