package com.itpropro.todo_app.domain.usecase.interfaces;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.helpers.Callback;

/**
 * Created by juank on 2/12/2017.
 */

public interface UserUseCase {

    void login(String email, String password, boolean remember, Callback<User> callback);

    void signUp(String fullName, String email, String password, Callback<User> callback);

    void recoveryPassword(String email, Callback<Boolean> callback);
}
