package com.example.helloworld.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.Ejerci;
import com.example.helloworld.R;

public class RepositoryFragment extends Fragment {

    private RepositoryViewModel repositoryViewModel;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        repositoryViewModel =
                new ViewModelProvider(this).get(RepositoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_repository, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        LinearLayout LinearTest = root.findViewById(R.id.linearLayout2);

        LinearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insira qualquer comportamento aqui. (:
                Intent intent = new Intent(getActivity(), Ejerci.class);
                startActivity(intent);

            }
        });
        repositoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;



    }
}