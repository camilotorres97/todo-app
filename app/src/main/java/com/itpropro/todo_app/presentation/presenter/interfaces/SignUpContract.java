package com.itpropro.todo_app.presentation.presenter.interfaces;

/**
 * Created by juank on 30/11/2017.
 */

public interface SignUpContract {

    interface View{
        void goToLoginFragment();

        void goToMainActivity();

        void showErrorMessage(Exception error);
    }

    interface UserActionsListener{
        void onSignUp(String nombre, String email, String password);
    }
}
