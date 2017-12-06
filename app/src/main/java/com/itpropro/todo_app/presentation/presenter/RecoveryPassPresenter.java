package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.domain.usecase.UserUseCase;
import com.itpropro.todo_app.domain.usecase.impl.PasswordRecoveryImpl;
import com.itpropro.todo_app.domain.usecase.impl.UserUseCaseImpl;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.view.fragment.RecoveryPasswordFragment;

/**
 * Created by PC on 04/12/2017.
 */

public class RecoveryPassPresenter implements RecoveryPassContract.UserActionsListener {

    private RecoveryPassContract.View view;
    private RecoveryPassContract.UserActionsListener userUseCase;

    public RecoveryPassPresenter(RecoveryPasswordFragment recoveryPasswordFragment){
        this.view = recoveryPasswordFragment;
            userUseCase = new PasswordRecoveryImpl();
    }

    @Override
    public void onRecovery(String email) {
        userUseCase.onRecovery(email);
    }
}
