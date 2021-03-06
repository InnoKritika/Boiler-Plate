package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.button.MaterialButton;

public class CreateProfileActivity extends AppCompatActivity {
    MaterialButton btnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        initView();

        adjustButtonSize(btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(CreateProfileActivity.this,MainActivity.class);
                startActivity(send);
            }
        });
    }

    private void initView() {
        btnContinue = findViewById(R.id.button_continue_2);
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
