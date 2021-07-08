package com.example.boilerplate.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.ModelClasses.Subject1;
import com.example.boilerplate.ModelClasses.Teacher;
import com.example.boilerplate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiipleCategoryDataFirebase extends AppCompatActivity {
    Button btnSave,btnReset,btnData;
    CheckBox c1,c2,c3,c4;
    FirebaseDatabase database;
    DatabaseReference reference;
    Teacher teacher;
    EditText etName;
    ArrayList<String> arrayList;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_category_data);

        btnSave = findViewById(R.id.btnSave);
        c4=findViewById(R.id.cbBiology);
        c3=findViewById(R.id.cbChemistry);
        c2=findViewById(R.id.cbPhysics);
        c1=findViewById(R.id.cbMaths);
        etName = findViewById(R.id.etTeacherName);
        btnReset = findViewById(R.id.btnReset);
        spinner = findViewById(R.id.spinnert);
        btnData = findViewById(R.id.btnData);

        String maths = "Maths";
        String physics = "Physics";
        String chemistry = "Chemistry";
        String biology = "Biology";
        reference = database.getInstance().getReference("teacher");
        teacher = new Teacher();





        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });*/

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = reference.push().getKey();
                String teacherName = etName.getText().toString();
                arrayList = new ArrayList<>();


                if (c1.isChecked()){

                    arrayList.add(c1.getText().toString());
                }else {
                    ////
                }
                if (c2.isChecked()){

                    arrayList.add(c2.getText().toString());

                }else {
                    ////
                }
                if (c3.isChecked()){
                    arrayList.add(c3.getText().toString());

                }else {
                    ////
                }
                if (c4.isChecked()){
                    arrayList.add(c4.getText().toString());

                }else {
                    ////
                }

                HashMap<String, Object> hashMap=new HashMap<>();
                for (int i=0;i<arrayList.size();i++){
                    hashMap.put("subject "+i, arrayList.get(i));
                }


//                String subjects = TextUtils.join(",",arrayList);
                Subject1 subject1 = new Subject1(id,teacherName,hashMap);
                Toast.makeText(MultiipleCategoryDataFirebase.this, id+"===="+teacherName+"====="+hashMap, Toast.LENGTH_SHORT).show();
                reference.child(id).setValue(subject1);
                Toast.makeText(MultiipleCategoryDataFirebase.this, "data added", Toast.LENGTH_SHORT).show();
            }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c1.isChecked())
                    c1.setChecked(false);
                if (c2.isChecked())
                    c2.setChecked(false);
                if (c3.isChecked())
                    c3.setChecked(false);
                if (c4.isChecked())
                    c4.setChecked(false);

                etName.getText().clear();

            }
        });
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
