package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    MaterialButton btnGenerateOTP, btnGoogle, btnFacebook;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        adjustButtonSize(btnFacebook);
        adjustButtonSize(btnGenerateOTP);
        adjustButtonSize(btnGoogle);

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otp = new Intent(LoginActivity.this,NumberVerificationActivity.class);
                startActivity(otp);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Google Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Facebook Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnGenerateOTP = findViewById(R.id.btnGenerateOTP);
        toolbar = findViewById(R.id.toolbar);
    }
    private void adjustButtonSize(MaterialButton btnLogin) {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        ViewGroup.LayoutParams params = btnLogin.getLayoutParams();
        params.height = ((height*75) / 1000);         // 10%
        params.width = ((width * 100) / 100); // 50%
        btnLogin.setLayoutParams(params);
    }
}
