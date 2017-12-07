package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.domain.usecase.interfaces.UserUseCase;
import com.itpropro.todo_app.domain.usecase.impl.UserUseCaseImpl;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.presenter.interfaces.LoginContract;
import com.itpropro.todo_app.presentation.view.fragment.LoginFragment;

/**
 * Created by juank on 30/11/2017.
 */

public class LoginPresenter implements LoginContract.UsersActionsListener{

    private LoginContract.View view;
    private UserUseCase userUseCase;

    public LoginPresenter(LoginFragment loginFragment) {
        this.view = loginFragment;
        this.userUseCase = new UserUseCaseImpl();
    }


    @Override
    public void onLogin(String email, String password, boolean remember) {
        userUseCase.login(email, password, remember, new Callback<User>() {
            @Override
            public void success(User result) {
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                view.showMessageError(error);
            }
        });
    }
}
