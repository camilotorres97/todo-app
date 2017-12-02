package com.itpropro.todo_app.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.itpropro.todo_app.R;
import com.itpropro.todo_app.helpers.Utilities;
import com.itpropro.todo_app.presentation.presenter.LoginContract;
import com.itpropro.todo_app.presentation.presenter.LoginPresenter;
import com.itpropro.todo_app.presentation.view.activity.AuthActivity;
import com.itpropro.todo_app.presentation.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.UsersActionsListener mActionsListener;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextView tvForgotPassword;
    private Switch swRemember;
    private Button btnStart;
    private Button btnDontHaveAccount;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mActionsListener = new LoginPresenter(this);

        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        swRemember = view.findViewById(R.id.swRemember);
        btnStart = view.findViewById(R.id.btnStart);
        btnDontHaveAccount = view.findViewById(R.id.btnDontHaveAccount);

        btnStart.setOnClickListener(this);
        btnDontHaveAccount.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                onLogin();
                break;
            case R.id.btnDontHaveAccount:
                goToSignUpFragment();
                break;
            case R.id.tvForgotPassword:
                goToRecoveryPassword();
                break;
        }
    }

    @Override
    public void goToSignUpFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(SignUpFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void goToRecoveryPassword(){
        RecoveryPasswordFragment recoveryPasswordFragment = RecoveryPasswordFragment.getInstance();
        recoveryPasswordFragment.show(getFragmentManager(),null);
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(),error.getMessage(),Snackbar.LENGTH_LONG).show();
    }

    private void onLogin(){
        try {
            boolean result = true;
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            boolean remember = swRemember.isChecked();

            if (Utilities.isEmpty(email)) {
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true); //habilita la sección de error
                result = false;
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            if (Utilities.isEmpty(password)) {
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true); //habilita la sección de error
                result = false;
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            //Si las validaciones no generaron errores
            if (result) {
                mActionsListener.onLogin(email, password, remember);
            }
            }catch(Exception e){

            }
        }

}
