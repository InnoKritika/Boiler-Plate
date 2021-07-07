package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NumberVerificationActivity extends AppCompatActivity {

    Button btnGenerateOTP;

    EditText etPhoneNumber;

    String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verification);
        findViews();

//        StartFirebaseLogin();

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber="+91"+etPhoneNumber.getText().toString();
                Intent sendUser = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                sendUser.putExtra("number",phoneNumber);
                startActivity(sendUser);
            }
        });

        /**/
    }



    private void findViews() {
        btnGenerateOTP=findViewById(R.id.btnSendOTP);
//        btnSignIn=findViewById(R.id.btnContinue1);

        etPhoneNumber=findViewById(R.id.et_number);
//        etOTP=findViewById(R.id.etOtP);
    }

    /*private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(NumberVerificationActivity.this, "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(NumberVerificationActivity.this,"verification fialed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(NumberVerificationActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
                Intent sendUser = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                sendUser.putExtra("number",phoneNumber);
                sendUser.putExtra("otp",verificationCode);
                startActivity(sendUser);
            }
        };
    }*/

}
