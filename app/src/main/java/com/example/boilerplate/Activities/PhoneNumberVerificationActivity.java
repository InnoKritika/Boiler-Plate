package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PhoneNumberVerificationActivity extends AppCompatActivity {
    EditText etnumber;
    Button btnSubmit;
    ProgressBar progressBar;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_otp);

        etnumber = findViewById(R.id.etNumber);
        btnSubmit = findViewById(R.id.btnNumber);
        progressBar = findViewById(R.id.progressBar);

        String number  =etnumber.getText().toString();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+etnumber,
                        60,
                        TimeUnit.SECONDS,PhoneNumberVerificationActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {

                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull  FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(PhoneNumberVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull  String verificationId, @NonNull  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);

                                Intent intent = new Intent(PhoneNumberVerificationActivity.this,OTPVerificationActivity.class);
                                intent.putExtra("mobile",number);
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);
                            }
                        });


            }
        });
    }
}
