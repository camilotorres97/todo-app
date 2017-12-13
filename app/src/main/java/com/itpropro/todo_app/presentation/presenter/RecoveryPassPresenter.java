package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.domain.usecase.interfaces.UserUseCase;
import com.itpropro.todo_app.domain.usecase.impl.UserUseCaseImpl;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.presenter.interfaces.RecoveryPassContract;

import java.util.List;

/**
 * Created by PC on 04/12/2017.
 */

public class RecoveryPassPresenter implements RecoveryPassContract.UserActionsListener {

    private RecoveryPassContract.View view;
    private UserUseCase userUseCase;

    public RecoveryPassPresenter(RecoveryPassContract.View recoveryPasswordFragment){
        this.view = recoveryPasswordFragment;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onRecovery(String email){
        userUseCase.recoveryPassword(email, new Callback<Boolean>() {

            @Override
            public void success(List<Todo> result) {
                view.showSuccessMessage();
            }

            @Override
            public void error(Exception error) {
                view.showErrorMessage(error);
            }
        });
    }
}
