package com.example.helloworld.ui.alarms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.R;

public class AlarmsFragment extends Fragment {

    private AlarmsViewModel alarmsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmsViewModel =
                new ViewModelProvider(this).get(AlarmsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alarms, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        alarmsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}