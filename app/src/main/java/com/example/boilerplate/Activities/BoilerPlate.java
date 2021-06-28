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
    MaterialButton btnImagePicker, btnStatteredImage,btnCrudOperations,btnMaterialDesign,btnCardView,btnBottomAppBars;
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
        btnCrudOperations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crud = new Intent(BoilerPlate.this,CrudOperations.class);
                startActivity(crud);
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
    }

    private void initView() {
        btnImagePicker = findViewById(R.id.btnImagePicker);
        btnStatteredImage = findViewById(R.id.btnStatteredImage);
        btnCrudOperations = findViewById(R.id.btnCrudOperations);
        btnMaterialDesign = findViewById(R.id.button_material_design);
        btnCardView = findViewById(R.id.buttonCardView);
        btnBottomAppBars = findViewById(R.id.bottomAppBars);
    }
}