package com.example.purplrcode;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class TransferFragment extends Fragment {

    private TransferViewModel mViewModel;
    private ImageView imageView;

    public static TransferFragment newInstance() {
        return new TransferFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transfer_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TransferViewModel.class);
        imageView.setX(imageView.getX() + mViewModel.dX);
        // TODO: Use the ViewModel
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "x", 0, 0);
        objectAnimator.setDuration(500);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()) {
                    float dx = new Random().nextBoolean() ? 100 : -100;
                    objectAnimator.setFloatValues(imageView.getX(), imageView.getX() + dx);
                    mViewModel.dX += dx;
                    objectAnimator.start();
                }

            }
        });
    }

}