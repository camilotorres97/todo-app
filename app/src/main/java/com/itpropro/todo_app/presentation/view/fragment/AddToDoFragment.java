package com.itpropro.todo_app.presentation.view.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itpropro.todo_app.R;
import com.itpropro.todo_app.helpers.Utilities;
import com.itpropro.todo_app.presentation.presenter.AddToDoPresenter;
import com.itpropro.todo_app.presentation.presenter.interfaces.AddToDoContract;
import com.itpropro.todo_app.presentation.view.activity.MainActivity;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddToDoFragment extends Fragment implements AddToDoContract.View, View.OnClickListener{

    private AddToDoContract.UserActionsListener mActionsListener;
    private TextInputLayout tilDescriptionActivity;
    private EditText etFechaFin;
    private Button btnAddActivity;
    private ImageView imageView;
    private FloatingActionButton fab;

    public AddToDoFragment() {
        // Required empty public constructor
    }

    public static AddToDoFragment getInstance(){
        return new AddToDoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_todo, container, false);

        mActionsListener = new AddToDoPresenter(this);

        tilDescriptionActivity = view.findViewById(R.id.tilDescriptionActivity);
        etFechaFin = view.findViewById(R.id.etFechaFin);
        btnAddActivity = view.findViewById(R.id.btnAddActivity);
        imageView = view.findViewById(R.id.ivImagen);

        btnAddActivity.setOnClickListener(this);
        imageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        fab.setVisibility(View.VISIBLE);
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //TODO implementar dialog para seleccionar fecha
            case R.id.btnAddActivity:
                onSave();
                break;
            case R.id.imageView:
                //TODO Implementar captura de foto
                break;

        }
    }

    @Override
    public void goToTodoListFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(ToDoListFragment.getInstance(), true);
    }

    @Override
    public void showErrorMessage(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    public void onSave(){
        try{
            Boolean result = true;
            String description = tilDescriptionActivity.getEditText().getText().toString();
            Date finishDate = new Date();

            if(Utilities.isEmpty(description)){
                tilDescriptionActivity.setError(getString(R.string.is_required));
                tilDescriptionActivity.setErrorEnabled(true);
                result = false;
            }else{
                tilDescriptionActivity.setError(null);
                tilDescriptionActivity.setErrorEnabled(false);
            }

            if(result){
                mActionsListener.onSave(description,false,finishDate,"");
            }
        }catch (Exception e){
            showErrorMessage(e);
        }
    }
}
