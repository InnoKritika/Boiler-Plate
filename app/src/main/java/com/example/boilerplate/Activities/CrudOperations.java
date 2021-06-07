package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.Adapters.CrudAdapter;
import com.example.boilerplate.ModelClasses.User;
import com.example.boilerplate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CrudOperations extends AppCompatActivity {
    Button addData;
    RecyclerView showData;
    CrudAdapter adapter;
    ArrayList<User> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_operations);
        addData = findViewById(R.id.btn_add_data);
        showData = findViewById(R.id.rv_crud_operations);
        list = new ArrayList<>();
        adapter = new CrudAdapter(this,list);
        showData.setAdapter(adapter);
        showData.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren() ){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(CrudOperations.this,RegistrationActivity.class);
                startActivity(add);
            }
        });

    }
}
