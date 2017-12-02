package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.domain.usecase.UserUseCase;
import com.itpropro.todo_app.domain.usecase.impl.UserUseCaseImpl;
import com.itpropro.todo_app.helpers.Callback;

/**
 * Created by juank on 30/11/2017.
 */

public class SignUpPresenter implements SignUpContract.UserActionsListener {

    private SignUpContract.View view;
    private UserUseCase userUseCase;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    public void onSignUp(String nombre, String email, String password) {
        userUseCase.signUp(nombre, email, password, new Callback<User>() {
            @Override
            public void sucess(User result) {
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                view.showErrorMessage(error);
            }
        });
    }
}
