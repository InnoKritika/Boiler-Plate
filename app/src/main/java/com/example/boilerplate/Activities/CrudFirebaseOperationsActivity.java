package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.Adapters.CrudFirebaseAdapter;
import com.example.boilerplate.ModelClasses.Crud;
import com.example.boilerplate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CrudFirebaseOperationsActivity extends AppCompatActivity {
    EditText etName, etStudentName;
    Button btnAdd, btnIndivisualData, btnSpecificData, btnCompleteData,btnLimitedData;
    Spinner spinner;
    DatabaseReference databaseReference;
    ListView listView;
    List<Crud> studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_firebase_operations);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        etName = findViewById(R.id.etName);
        btnAdd = findViewById(R.id.btnAddUser);
        spinner = findViewById(R.id.spinnerSubject);
        listView = findViewById(R.id.listView);
        etStudentName = findViewById(R.id.etStudentName);
        btnSpecificData = findViewById(R.id.btnSpecificData);
        btnCompleteData = findViewById(R.id.btnFullData);
        btnLimitedData = findViewById(R.id.btnLimitedData);
        btnIndivisualData = findViewById(R.id.btnIndivisualData);

        studentList = new ArrayList<>();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Crud crud = studentList.get(position);
                updateDialog(crud.getUserId(), crud.getName());

                return false;
            }
        });

        btnIndivisualData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = etName.getText().toString();
                Query query2 = FirebaseDatabase.getInstance().getReference("students").orderByChild("name").equalTo(userName);
                query2.addListenerForSingleValueEvent(valueEventListener);

            }
        });

        btnCompleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(valueEventListener);
            }
        });

        btnSpecificData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query3 = FirebaseDatabase.getInstance().getReference("students").limitToFirst(2);
                query3.addListenerForSingleValueEvent(valueEventListener);
            }
        });
        btnLimitedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query3 = FirebaseDatabase.getInstance().getReference("students").limitToFirst(2).limitToLast(6);
                query3.addListenerForSingleValueEvent(valueEventListener);
            }
        });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            studentList.clear();
            for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                Crud crud = studentSnapshot.getValue(Crud.class);
                studentList.add(crud);
            }

            CrudFirebaseAdapter adapter = new CrudFirebaseAdapter(CrudFirebaseOperationsActivity.this, studentList);
            listView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void updateDialog(String studentId, String studentName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);


        final EditText etName = dialogView.findViewById(R.id.etUpdateName);
        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdateStudent);
        final Spinner spinner1 = dialogView.findViewById(R.id.spinnerUpdate);
        final Button btnDelete = dialogView.findViewById(R.id.btnDeleteStudent);

        dialogBuilder.setTitle("Updating Student ... " + studentName);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String subject = spinner1.getSelectedItem().toString();
                if (!TextUtils.isEmpty(name)) {
                    updateStudent(studentId, name, subject);
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(CrudFirebaseOperationsActivity.this, "Please enter updated name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(studentId);
                alertDialog.dismiss();
            }
        });


    }

    private void deleteStudent(String studentId) {
        DatabaseReference bdStudent = FirebaseDatabase.getInstance().getReference("students").child(studentId);
        bdStudent.removeValue();
        Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
    }


    private boolean updateStudent(String id, String name, String subject) {
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("students").child(id);
        Crud crud = new Crud(id, name, subject);
        databaseReference1.setValue(crud);
        Toast.makeText(this, "Student data Updated Successfully...", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void addUser() {
        String name = etName.getText().toString().trim();
        String subject = spinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)) {
            String id = databaseReference.push().getKey();
            Crud crud = new Crud(id, name, subject);
            databaseReference.child(id).setValue(crud);
            Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Enter name please", Toast.LENGTH_SHORT).show();
        }
    }
}
