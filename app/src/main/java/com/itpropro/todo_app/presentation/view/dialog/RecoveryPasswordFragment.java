package com.itpropro.todo_app.presentation.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.itpropro.todo_app.R;
import com.itpropro.todo_app.helpers.Utilities;
import com.itpropro.todo_app.presentation.presenter.interfaces.RecoveryPassContract;
import com.itpropro.todo_app.presentation.presenter.RecoveryPassPresenter;


    public class RecoveryPasswordFragment extends DialogFragment implements RecoveryPassContract.View,View.OnClickListener {

    private RecoveryPassContract.UserActionsListener mActionsListener;
    private View view;
    private EditText eTEmail;
    private TextView tvRecover;
    private TextView tvMessage;
    private TextView tvClose;

    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }

    public static RecoveryPasswordFragment getInstance(){
        return new RecoveryPasswordFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_password_recovery, null);

        mActionsListener = new RecoveryPassPresenter(this);

        eTEmail = view.findViewById(R.id.etEmail);
        tvRecover = view.findViewById(R.id.tvRecover);
        tvMessage = view.findViewById(R.id.tvMessage);
        tvClose = view.findViewById(R.id.tvAccept);

        tvRecover.setOnClickListener(this);
        tvClose.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tvRecover:
                onRecovery();
                break;
            case R.id.tvAccept:
                dismiss();
                break;
        }
    }

    @Override
    public void showErrorMessage(Exception error) {
        tvMessage.setText(error.getMessage());
        tvMessage.setTextColor(getResources().getColor(R.color.error_message));
        tvMessage.setVisibility(view.VISIBLE);
    }

    @Override
    public void showSuccessMessage() {
        tvMessage.setText(R.string.recovery_email_sent);
        tvMessage.setTextColor(getResources().getColor(R.color.success_message));
        tvRecover.setVisibility(View.GONE);
        tvMessage.setVisibility(View.VISIBLE);
    }

    private void onRecovery() {
        try {
            boolean result = true;

            String email = eTEmail.getText().toString();
            if(Utilities.isEmpty(email)) {
                eTEmail.setError(getString(R.string.is_required));
                result = false;
            } else {
                eTEmail.setError(null);
            }

            if(result) {
                mActionsListener.onRecovery(email);
            }
        } catch (Exception e) {
            showErrorMessage(e);
        }
    }

}
