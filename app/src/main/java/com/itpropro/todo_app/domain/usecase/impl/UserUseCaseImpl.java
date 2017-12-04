package com.itpropro.todo_app.domain.usecase.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.domain.usecase.UserUseCase;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.repository.UserRepository;
import com.itpropro.todo_app.repository.impl.UserFirebaseRepository;

/**
 * Created by juank on 2/12/2017.
 */

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;

    public UserUseCaseImpl(){
        this.userRepository = new UserFirebaseRepository();
    }

    @Override
    public void login(String email, String password, final boolean remember, final Callback<User> callback) {
        userRepository.login(email, password, new Callback<User>() {
            @Override
            public void sucess(User user) {

                if(user != null && remember) {
                    //TODO Guardar Email en SharedPreferences
                }
                callback.sucess(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });

        userRepository.login(email,password,callback);

    }

    @Override
    public void signUp(String fullName, String email, String password, final Callback<User> callback) {
        final User user = new User(fullName,email,password);

        userRepository.signUp(user, new Callback<User>() {
            @Override
            public void sucess(User result) {
                callback.sucess(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

    @Override
    public void recoveryPass(final String email, final Callback<User> callback) {

        userRepository.recoveryPass(email, new Callback<User>() {
            @Override
            public void sucess(User user) {

                callback.sucess(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

}
