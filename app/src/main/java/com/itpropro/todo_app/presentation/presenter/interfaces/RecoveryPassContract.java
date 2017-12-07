package com.itpropro.todo_app.presentation.presenter.interfaces;

/**
 * Created by PC on 04/12/2017.
 */

public interface RecoveryPassContract {

    interface View{
        void showErrorMessage(Exception error);

        void showSuccessMessage();
    }

    interface UserActionsListener{
        void onRecovery(String email);
    }

}
