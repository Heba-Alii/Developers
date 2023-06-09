package com.example.developers.view.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.developers.R;
import com.example.developers.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginFragment = inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.bind(loginFragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpannableString register = new SpannableString(getString(R.string.register));
        register.setSpan(new UnderlineSpan(), 0, register.length(), 0);
        binding.createNewAccount.setText(register);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.mail.getText().toString();
                String pass = binding.pass.getText().toString();
                if (dataIsValid(mail, pass)) {
                    binding.loginProgress.setVisibility(View.VISIBLE);
                    binding.login.setVisibility(View.VISIBLE);
                    fireBaseLogin(mail, pass);
                } else {
                    binding.loginProgress.setVisibility(View.GONE);
                    binding.login.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), R.string.complete, Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
    }

    private void fireBaseLogin(String mail, String pass) {
        firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                } else {
                    binding.loginProgress.setVisibility(View.GONE);
                    binding.login.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean dataIsValid(String mail, String pass) {
        return !mail.isEmpty() && !pass.isEmpty();
    }
}