package com.example.boilerplate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boilerplate.R;

public class BoilerPlate extends AppCompatActivity {
    Button btnImagePicker, btnStatteredImage,btnMaterialDesign,btnCardView,btnBottomAppBars,btnTopAppBar,btnRewardScreen,btnDialog;
    Button btnCrudFirebase,btnDynamicStorage,btnOTPVerification,btnGoogleSignin,btnFacebookSignin,btnUclicks,btnMultipleCategory,btnHashmapDatabase;
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
        btnDynamicStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent location = new Intent(BoilerPlate.this,DynamicStorageLocationActivity.class);
                startActivity(location);
            }
        });
        btnOTPVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otp = new Intent(BoilerPlate.this,PhoneNumberVerificationActivity.class);
                startActivity(otp);
            }
        });
        btnGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google = new Intent(BoilerPlate.this,GoogleFirebaseActivity.class);
                startActivity(google);
            }
        });
        btnFacebookSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebook = new Intent(BoilerPlate.this,FacebookFirebaseActivity.class);
                startActivity(facebook);
            }
        });
        btnUclicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uclicks = new Intent(getApplicationContext(),WelcomeActivity.class);
                startActivity(uclicks);
            }
        });
        btnMultipleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category = new Intent(getApplicationContext(),MultiipleCategoryDataFirebase.class);
                startActivity(category);
            }
        });
        btnHashmapDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hashmap = new Intent(getApplicationContext(), GetWallPaperActivity.class);
                startActivity(hashmap);
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
        btnDynamicStorage = findViewById(R.id.btnDynamicStorage);
        btnOTPVerification = findViewById(R.id.btnOTPVerification);
        btnGoogleSignin = findViewById(R.id.btnGoogleSignin);
        btnFacebookSignin = findViewById(R.id.btnFacebookSignin);
        btnUclicks = findViewById(R.id.btnUclicks);
        btnMultipleCategory = findViewById(R.id.btnMultipleCategory);
        btnHashmapDatabase = findViewById(R.id.btnHashmapDatabase);
    }
}