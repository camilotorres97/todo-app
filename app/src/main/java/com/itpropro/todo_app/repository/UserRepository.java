package com.itpropro.todo_app.repository;

import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.helpers.Callback;

/**
 * Created by juank on 30/11/2017.
 */

public interface UserRepository {

    void login(String email, String password, Callback<User> callback);

    void signUp(User user, Callback<User> callback);
}
