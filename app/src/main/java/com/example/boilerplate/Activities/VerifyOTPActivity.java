package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    MaterialButton btnContinue;
    TextView phoneNumber;
    FirebaseAuth auth;
    String otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;
    EditText etotp1,etotp2,etotp3,etotp4,etotp5,etotp6;
    String otp1,otp2,otp3,otp4,otp5,otp6;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        phoneNumber = findViewById(R.id.tvNumber);
        btnContinue=findViewById(R.id.btnContinue);
        etotp1 = findViewById(R.id.OTP1);
        etotp2 = findViewById(R.id.OTP2);
        etotp3 = findViewById(R.id.OTP3);
        etotp4 = findViewById(R.id.OTP4);
        etotp5 = findViewById(R.id.OTP5);
        etotp6 = findViewById(R.id.OTP6);
        String number = getIntent().getStringExtra("number");
        phoneNumber.setText(number);

        otp1 = etotp1.getText().toString();
        otp2 = etotp2.getText().toString();
        otp3 = etotp3.getText().toString();
        otp4 = etotp4.getText().toString();
        otp5 = etotp5.getText().toString();
        otp6 = etotp6.getText().toString();

        StartFirebaseLogin();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                VerifyOTPActivity.this,       // Activity (for callback binding)
                mCallback);                      // OnVerificationStateChangedCallbacks

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               otp=otp1+otp2+otp3+otp4+otp5+otp6;
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);

                SigninWithPhone(credential);


            }
        });
        if (verificationCode == null && savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(getApplicationContext(), "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(),"verification fialed",Toast.LENGTH_SHORT).show();
                Log.e("failed","===>"+e);
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(getApplicationContext(),"Code sent",Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VERIFICATION_ID,verificationCode);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        verificationCode = savedInstanceState.getString(KEY_VERIFICATION_ID);
    }
}
