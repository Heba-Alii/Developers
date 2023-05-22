package com.example.developers.view.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.developers.R;
import com.example.developers.controller.LocalBuilder;
import com.example.developers.databinding.FragmentHomeBinding;
import com.example.developers.model.pojo.DeveloperEntity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    Bundle bundle = new Bundle();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeFragment = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.bind(homeFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addDeveloperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_addDeveloperFragment);

            }
        });
        binding.cardFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("title", "Fresh");

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_allDeveloperFragment, bundle);
            }
        });
        binding.cardJunior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("title", "Junior");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_allDeveloperFragment, bundle);

            }
        });
        binding.cardMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("title", "Mid");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_allDeveloperFragment, bundle);

            }
        });
        binding.cardSenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("title", "Senior");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_allDeveloperFragment, bundle);

            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Log out?")
                        .setMessage("Are you sure you want to log out?")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                firebaseAuth.signOut();
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(R.drawable.baseline_add_alert_24).show();
            }
        });
    }
}