package com.example.purplrcode;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class OutFragment extends Fragment {

    private MyViewModel mViewModel;

    private Button buttonSubmit;
    private EditText editTextName,editTextClass,editTextStu;

    public static OutFragment newInstance() {
        return new OutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.out_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        mViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(MyViewModel.class);
        buttonSubmit = activity.findViewById(R.id.buttonSubmit);
        editTextName = activity.findViewById(R.id.editTextName);
        editTextClass = activity.findViewById(R.id.editTextClass);
        editTextStu = activity.findViewById(R.id.editTextStu);
        buttonSubmit.setEnabled(false);
        editTextName.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextName,0);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = editTextName.getText().toString().trim();
                String gradeClass = editTextClass.getText().toString().trim();
                String stu = editTextStu.getText().toString().trim();
                buttonSubmit.setEnabled((!name.isEmpty() && !gradeClass.isEmpty() && !stu.isEmpty()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextName.addTextChangedListener(textWatcher);
        editTextClass.addTextChangedListener(textWatcher);
        editTextStu.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String gradeClass = editTextClass.getText().toString().trim();
                String stu = editTextStu.getText().toString().trim();

                mViewModel.submit(name,gradeClass,stu);
                //mViewModel.save();

                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });

    }

}