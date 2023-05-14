package com.example.developers.view.developers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.developers.R;
import com.example.developers.controller.LocalBuilder;
import com.example.developers.databinding.FragmentAddDeveloperBinding;
import com.example.developers.databinding.FragmentAllDeveloperBinding;
import com.example.developers.model.pojo.DeveloperEntity;

import java.util.List;


public class AllDeveloperFragment extends Fragment implements DeleteDeveloper {
    FragmentAllDeveloperBinding binding;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View allDeveloperFragment = inflater.inflate(R.layout.fragment_all_developer, container, false);
        binding = FragmentAllDeveloperBinding.bind(allDeveloperFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDeveloperByTitle(title);
    }

    private void getDeveloperByTitle(String title) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalBuilder localBuilder = LocalBuilder.getInstance(getActivity());
                List<DeveloperEntity> developerEntityList = localBuilder.developerDao().getdeveloperByTitle(title);
                DeveloperAdapter developerAdapter = new DeveloperAdapter(developerEntityList, AllDeveloperFragment.this);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.developerRecycler.setAdapter(developerAdapter);
                        binding.developerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                });
            }
        }).start();
    }

    @Override
    public void deleteDeveloperById(int developerId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalBuilder localBuilder = LocalBuilder.getInstance(getContext());
                localBuilder.developerDao().deleteDeveloper(developerId);
            }
        }).start();
        Toast.makeText(getContext(), "Developer Deleted Successfully", Toast.LENGTH_SHORT).show();
    }
}