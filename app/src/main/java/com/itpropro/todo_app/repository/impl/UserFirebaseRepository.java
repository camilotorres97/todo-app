package com.itpropro.todo_app.repository.impl;

import android.support.annotation.NonNull;
import android.telecom.Call;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itpropro.todo_app.domain.model.User;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.repository.UserRepository;

/**
 * Created by juank on 30/11/2017.
 */

public class UserFirebaseRepository implements UserRepository {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserFirebaseRepository(){
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance()
                .getReference("users");
    }

    @Override
    public void login(String email, String password, final Callback<User> callback) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*If sign in fails, display a message to the user. If Sign in suceeds
                        the auth state Listener will be notified and logic to handle the
                        signed in user can be handled in the Listener*/
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            mDatabase.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    callback.sucess(user);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    callback.error(databaseError.toException());
                                }
                            });
                        }else{
                            callback.error(task.getException());
                        }
                    }
                });

    }

    @Override
    public void signUp(final User user, final Callback<User> callback) {

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Respuesta de la creación del usuario en FireBaseAuthentication
                        if(task.isSuccessful() && task.getResult() != null){
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            user.setPassword(null);
                            mDatabase.child(user.getId()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //Respuesta de la creación del usuario en FireBaseDatabase
                                        if(task.isSuccessful()){
                                            callback.sucess(user);
                                        }else{
                                            callback.error(task.getException());
                                        }
                                    }
                                });
                        }
                    }
                });

    }

    @Override
    public void recoveryPass(final String email, final Callback<User> callback) {

        mAuth.sendPasswordResetEmail(email);

    }
}