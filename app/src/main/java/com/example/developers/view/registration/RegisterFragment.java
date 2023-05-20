package com.example.developers.view.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.developers.R;
import com.example.developers.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerFragment = inflater.inflate(R.layout.fragment_register, container, false);
        binding = FragmentRegisterBinding.bind(registerFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpannableString login = new SpannableString("Log In");
        login.setSpan(new UnderlineSpan(), 0, login.length(), 0);
        binding.login.setText(login);
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.mail.getText().toString();
                String pass = binding.pass.getText().toString();
                if (datavalid(mail, pass)) {
                    binding.registerProgress.setVisibility(View.VISIBLE);
                    binding.register.setVisibility(View.GONE);
                    addToFireBase(mail, pass);

                } else {
                    binding.registerProgress.setVisibility(View.GONE);
                    binding.register.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Please Complete your data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }

    private void addToFireBase(String mail, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_registerFragment_to_homeFragment);
                } else {
                    binding.registerProgress.setVisibility(View.GONE);
                    binding.register.setVisibility(View.VISIBLE);
                    Log.e("TAG", "onComplete: " + task.getException().getMessage());
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean datavalid(String mail, String pass) {
        return !mail.isEmpty() && !pass.isEmpty();
    }
}