package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class NumberVerificationActivity extends AppCompatActivity {

    MaterialButton btnSendOTP;
    //MaterialToolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verification);

       // toolbar = findViewById(R.id.toolbar);
        btnSendOTP = findViewById(R.id.btnSendOTP);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Enter Number");

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(NumberVerificationActivity.this,VerifyOTPActivity.class);
                startActivity(send);
            }
        });
    }
}
