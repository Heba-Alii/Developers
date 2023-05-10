package com.example.developers.view.developers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.developers.R;
import com.example.developers.databinding.FragmentAddDeveloperBinding;


public class AddDeveloperFragment extends Fragment {
    FragmentAddDeveloperBinding binding;
    private String name = "", title = "";
    private double salary = 0.0, bonus = 0.0;
    private int vacatio = 0, absence = 0, medicalIns = 0, socialIns = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addDeveloperFragment = inflater.inflate(R.layout.fragment_add_developer, container, false);
        binding = FragmentAddDeveloperBinding.bind(addDeveloperFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.fresh: {
                        title = "Fresh";
                        binding.textSalaryHint.setText("Salary from 4000 - 7000");
                        binding.medicalInsurance.setVisibility(View.GONE);
                        binding.socialInsurance.setVisibility(View.GONE);
                        binding.bonus.setVisibility(View.GONE);
                        break;
                    }
                    case R.id.junior: {
                        title = "Junior";
                        binding.textSalaryHint.setText("Salary from 7000 - 10000");
                        binding.medicalInsurance.setVisibility(View.GONE);
                        binding.socialInsurance.setVisibility(View.GONE);
                        binding.bonus.setVisibility(View.VISIBLE);
                        break;

                    }
                    case R.id.mid: {
                        title = "Mid";
                        binding.textSalaryHint.setText("Salary from 10000 - 15000");
                        binding.medicalInsurance.setVisibility(View.VISIBLE);
                        binding.socialInsurance.setVisibility(View.GONE);
                        binding.bonus.setVisibility(View.VISIBLE);
                        break;
                    }
                    case R.id.senior: {
                        title = "Senior";
                        binding.textSalaryHint.setText("Salary from 15000 - 20000");
                        binding.medicalInsurance.setVisibility(View.VISIBLE);
                        binding.socialInsurance.setVisibility(View.VISIBLE);
                        binding.bonus.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
        binding.addDeveloperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}