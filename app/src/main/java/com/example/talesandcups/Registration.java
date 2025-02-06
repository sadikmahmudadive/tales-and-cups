package com.example.talesandcups;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.talesandcups.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Registration extends BaseActivity {

    ActivityRegistrationBinding binding;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to the onbording_2 screen
                Intent intent = new Intent(Registration.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#121223"));
    }

    private void setVariable() {
        binding.btnSignup.setOnClickListener(v -> {
            String email = binding.editTextTextEmail.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();

            if (password.length() < 6) {
                Toast.makeText(Registration.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, task -> {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: ");
                    Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, LoginActivity.class));
                }else {
                    Log.i(TAG, "failed: " + task.getException());
                    Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}