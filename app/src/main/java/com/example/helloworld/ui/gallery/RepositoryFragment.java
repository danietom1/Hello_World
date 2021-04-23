package com.example.helloworld.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.R;

public class RepositoryFragment extends Fragment {

    private RepositoryViewModel repositoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        repositoryViewModel = new ViewModelProvider(this).get(RepositoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        repositoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}