package com.itpropro.todo_app.presentation.presenter;

/**
 * Created by PC on 04/12/2017.
 */

public interface RecoveryPassContract {

    interface View{
        void goToLoginFragment();

        void goToMainActivity();

        void showErrorMessage(Exception error);
    }

    interface UserActionsListener{
        void onRecovery(String email);
    }

}
