package com.example.boilerplate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boilerplate.R;

public class BoilerPlate extends AppCompatActivity {
    Button btnImagePicker, btnStatteredImage,btnCrudOperations,btnMaterialDesign;
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
    }

    private void initView() {
        btnImagePicker = findViewById(R.id.btnImagePicker);
        btnStatteredImage = findViewById(R.id.btnStatteredImage);
        btnCrudOperations = findViewById(R.id.btnCrudOperations);
        btnMaterialDesign = findViewById(R.id.button_material_design);
    }
}