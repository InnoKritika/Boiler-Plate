package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {
    MaterialButton btnLogin, btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        adjustButtonSize(btnLogin);
        adjustButtonSize(btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });
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

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
