package com.itpropro.todo_app.presentation.presenter.interfaces;

import java.util.Date;

/**
 * Created by juank on 11/12/2017.
 */

public interface AddToDoContract {

    interface View{

        void goToTodoListFragment();

        void showErrorMessage(Exception error);
    }

    interface UserActionsListener{

        void onSave(String description, boolean finished, Date finishDate, String image);
    }

}
