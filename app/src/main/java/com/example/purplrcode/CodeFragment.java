package com.example.purplrcode;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.purplrcode.databinding.CodeFragmentBinding;

public class CodeFragment extends Fragment {

    private MyViewModel mViewModel;
    private TextView textViewTime, textViewDate;
    private TextView textViewName,textViewClass,textViewStu1,textViewStu2;
    private ImageView imageViewIcon;

    private DateUtil timer;
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private Uri uri;

    private static final String ACTIVITY_TAG="LogDemo";

    public static CodeFragment newInstance() {
        return new CodeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(getActivity().getApplication(), this)).get(MyViewModel.class);
//        CodeFragmentBinding binding;
//        binding = DataBindingUtil.inflate(inflater, R.layout.code_fragment, container, false);
//        binding.setData(mViewModel);
//        binding.setLifecycleOwner(requireActivity());
        return inflater.inflate(R.layout.code_fragment, container, false);
        //return binding.getRoot();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
        imageViewIcon = requireActivity().findViewById(R.id.imageViewIcon);
        textViewTime = requireActivity().findViewById(R.id.textViewTime);
        textViewDate = requireActivity().findViewById(R.id.textViewDate);
        textViewName = requireActivity().findViewById(R.id.textViewName);
        textViewClass = requireActivity().findViewById(R.id.textViewClass);
        textViewStu1 = requireActivity().findViewById(R.id.textViewStu1);
        textViewStu2 = requireActivity().findViewById(R.id.textViewStu2);

        textViewTime.setText(timer.getNowTime());
        textViewDate.setText(timer.getNowDateTime());
        new TimeThread().start();
        mViewModel.getUri().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                uri = Uri.parse(s);
                imageViewIcon.setImageURI(uri);
            }
        });
        mViewModel.getMyName().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewName.setText(s);
                Log.d(ACTIVITY_TAG, "onChanged: "+s);
            }
        });
        mViewModel.getMyClass().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewClass.setText(s);

            }
        });
        mViewModel.getStu().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewStu1.setText(s);
                textViewStu2.setText(s);
            }
        });


    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            super.run();
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);

        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textViewTime.setText(timer.getNowTime());
                    break;
            }
            return false;
        }
    });

}