package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.domain.usecase.UserUseCase;
import com.itpropro.todo_app.domain.usecase.impl.UserUseCaseImpl;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.view.fragment.RecoveryPasswordFragment;

/**
 * Created by PC on 04/12/2017.
 */

public class RecoveryPassPresenter implements RecoveryPassContract.UserActionsListener {

    private RecoveryPassContract.View view;
    private UserUseCase userUseCase;

    public RecoveryPassPresenter(RecoveryPasswordFragment recoveryPasswordFragment){
        this.view = recoveryPasswordFragment;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onRecovery(String email) {
        userUseCase.recoveryPass(email, new Callback<User>() {
            @Override
            public void sucess(User result) {
                view.goToLoginFragment();
            }

            @Override
            public void error(Exception error) {
                view.showErrorMessage(error);
            }
        });
    }
}
