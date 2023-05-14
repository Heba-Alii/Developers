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
import android.widget.Toast;

import com.example.developers.R;
import com.example.developers.controller.LocalBuilder;
import com.example.developers.databinding.FragmentAddDeveloperBinding;
import com.example.developers.model.pojo.DeveloperEntity;


public class AddDeveloperFragment extends Fragment {
    FragmentAddDeveloperBinding binding;
    private String name = "", title = "";
    private double salary = 0.0, bonus = 0.0;
    private int vacation = 0, absence = 0, medicalIns = 0, socialIns = 0;


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
                name = binding.developerName.getText().toString();
                absence = Integer.parseInt(binding.absence.getText().toString().isEmpty() ? "0" : binding.absence.getText().toString());
                vacation = Integer.parseInt(binding.vacation.getText().toString().isEmpty() ? "0" : binding.vacation.getText().toString());
                salary = Double.parseDouble(binding.salary.getText().toString().isEmpty() ? "0" : binding.salary.getText().toString());
                bonus = Double.parseDouble(binding.bonus.getText().toString().isEmpty() ? "0" : binding.bonus.getText().toString());
                medicalIns = Integer.parseInt(binding.medicalInsurance.getText().toString().isEmpty() ? "0" : binding.medicalInsurance.getText().toString());
                socialIns = Integer.parseInt(binding.socialInsurance.getText().toString().isEmpty() ? "0" : binding.socialInsurance.getText().toString());
                if (isDataValid()) {
                    addDataToRoom();
                } else {
                    Toast.makeText(getActivity(), "Please Complete Your Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addDataToRoom() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeveloperEntity developer = new DeveloperEntity(name, title, salary, bonus, absence, vacation, medicalIns, socialIns);
                LocalBuilder localBuilder = LocalBuilder.getInstance(getActivity());
                localBuilder.developerDao().addDeveloper(developer);
            }
        }).start();
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }

    private boolean isDataValid() {
        return !name.isEmpty() && !title.isEmpty() && !binding.salary.getText().toString().isEmpty();
    }
}