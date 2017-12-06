package com.itpropro.todo_app.domain.usecase.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.itpropro.todo_app.presentation.presenter.RecoveryPassContract;

/**
 * Created by juank on 5/12/2017.
 */

public class PasswordRecoveryImpl implements RecoveryPassContract.UserActionsListener {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private RecoveryPassContract.UserActionsListener userAction;

    public PasswordRecoveryImpl() {
        this.userAction = userAction;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onRecovery(String email) {
        if(email.length() <= 0){
            //TODO Implementar mensaje de error
        }else{
            firebaseAuth.sendPasswordResetEmail(email);
        }
    }
}
