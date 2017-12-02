package com.itpropro.todo_app.presentation.presenter;

/**
 * Created by juank on 30/11/2017.
 */

public interface LoginContract {

    interface View{
        void goToSignUpFragment();

        void goToMainActivity();

        void showMessageError(Exception error);
    }

    interface  UsersActionsListener{
        void onLogin(String email, String password, boolean remember);
    }

}
