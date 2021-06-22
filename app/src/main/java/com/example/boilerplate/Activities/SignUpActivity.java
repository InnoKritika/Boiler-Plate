package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    MaterialButton btnGenerateOTP, btnGoogle, btnFacebook;
    MaterialToolbar toolbar;
    TextView toolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        toolbar.setTitle("");
        toolbarTitle.setText("");
        setSupportActionBar(toolbar);

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otp = new Intent(SignUpActivity.this,NumberVerificationActivity.class);
                startActivity(otp);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "Google Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "Facebook Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnGenerateOTP = findViewById(R.id.btnGenerateOTP);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
    }
}
