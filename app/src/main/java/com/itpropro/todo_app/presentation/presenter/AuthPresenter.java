package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.presentation.presenter.interfaces.AuthContract;
import com.itpropro.todo_app.presentation.presenter.interfaces.AuthContract.UserActionsLister;

/**
 * Created by juank on 30/11/2017.
 */

public class AuthPresenter implements UserActionsLister {

    private AuthContract.View view;

    public AuthPresenter(AuthContract.View view) {
        this.view = view;
    }

    public void goToFirstFragment() {
        view.goToLoginFragment();
    }
}
