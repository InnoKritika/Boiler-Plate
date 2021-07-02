package com.example.boilerplate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boilerplate.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;

public class BoilerPlate extends AppCompatActivity {
    Button btnImagePicker, btnStatteredImage,btnMaterialDesign,btnCardView,btnBottomAppBars,btnTopAppBar,btnRewardScreen,btnDialog;
    Button btnCrudFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boiler_plate);
        initView();
        btnImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image = new Intent(BoilerPlate.this,AddingImageActivity.class);
                startActivity(image);
            }
        });
        btnStatteredImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statter = new Intent(BoilerPlate.this,StatterdImagesActivity.class);
                startActivity(statter);
            }
        });

        btnMaterialDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent material = new Intent(BoilerPlate.this,WelcomeActivity.class);
                startActivity(material);
            }
        });
        btnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card = new Intent(BoilerPlate.this,CardViewActivity.class);
                startActivity(card);
            }
        });
        btnBottomAppBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bottomAppbar = new Intent(BoilerPlate.this, BottomAppBarActivity.class);
                startActivity(bottomAppbar);
            }
        });
        btnTopAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent topAppBar = new Intent(BoilerPlate.this,TopAppBarActivity.class);
                startActivity(topAppBar);
            }
        });
        btnRewardScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reward = new Intent(BoilerPlate.this,RewardScreenActivity.class);
                startActivity(reward);
            }
        });
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialog = new Intent(BoilerPlate.this,DialogActivity.class);
                startActivity(dialog);
            }
        });
        btnCrudFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent firebase = new Intent(BoilerPlate.this,CrudFirebaseOperationsActivity.class);
                startActivity(firebase);
            }
        });
    }

    private void initView() {
        btnImagePicker = findViewById(R.id.btnImagePicker);
        btnStatteredImage = findViewById(R.id.btnStatteredImage);
        btnMaterialDesign = findViewById(R.id.button_material_design);
        btnCardView = findViewById(R.id.buttonCardView);
        btnBottomAppBars = findViewById(R.id.bottomAppBars);
        btnTopAppBar = findViewById(R.id.topNavigationBar);
        btnRewardScreen = findViewById(R.id.btnRewardUclicks);
        btnDialog = findViewById(R.id.btnDailog);
        btnCrudFirebase =  findViewById(R.id.btnCrudFirebase);
    }
}