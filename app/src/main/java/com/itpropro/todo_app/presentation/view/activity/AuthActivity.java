package com.itpropro.todo_app.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itpropro.todo_app.R;
import com.itpropro.todo_app.presentation.presenter.interfaces.AuthContract;
import com.itpropro.todo_app.presentation.presenter.AuthPresenter;
import com.itpropro.todo_app.presentation.view.fragment.LoginFragment;

public class AuthActivity extends AppCompatActivity implements AuthContract.View{

    private AuthContract.UserActionsLister mActionsListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mActionsListener = new AuthPresenter(this);
        mActionsListener.goToFirstFragment();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void goToLoginFragment() {
        replaceFragment(LoginFragment.getInstance(),true);
    }


    @Override
    public void goToMainActivity() {

    }
}
