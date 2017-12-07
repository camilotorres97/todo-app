package com.itpropro.todo_app.presentation.presenter.interfaces;

/**
 * Created by juank on 30/11/2017.
 */

public interface AuthContract {

    //Interface que implementará el Fragment o Activity
    interface View{
        void goToLoginFragment();

        void goToMainActivity();
    }

    //Interface que implementará el presenter
    interface UserActionsLister{
        void goToFirstFragment();
    }
}
