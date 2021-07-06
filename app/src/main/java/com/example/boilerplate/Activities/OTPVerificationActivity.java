package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;

import java.util.concurrent.TimeUnit;

public class OTPVerificationActivity extends AppCompatActivity {

    EditText etOTP;
    Button btnOTPSend;
    String phoneNumber;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        etOTP = findViewById(R.id.etOTP1);
        btnOTPSend = findViewById(R.id.btnSubmitOTP);
        phoneNumber = getIntent().getStringExtra("mobile").toString();

        initiateOtp();

        btnOTPSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTPVerificationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initiateOtp() {

    }
}
