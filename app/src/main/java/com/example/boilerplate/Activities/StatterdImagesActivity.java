package com.example.boilerplate.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.boilerplate.Adapters.ImageAdapter;
import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;

import java.util.ArrayList;

public class StatterdImagesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter adapter;
    ArrayList<Image> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statterd_image);
        recyclerView = findViewById(R.id.rv);
        arrayList  =new ArrayList<>();

        arrayList.add(new Image(R.drawable.image10));
        arrayList.add(new Image(R.drawable.image11));
        arrayList.add(new Image(R.drawable.image12));
        arrayList.add(new Image(R.drawable.image13));
        arrayList.add(new Image(R.drawable.image14));
        arrayList.add(new Image(R.drawable.image15));
        arrayList.add(new Image(R.drawable.image16));
        arrayList.add(new Image(R.drawable.image17));
        arrayList.add(new Image(R.drawable.image18));
        arrayList.add(new Image(R.drawable.image19));
        arrayList.add(new Image(R.drawable.image20));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ImageAdapter(getApplicationContext(),arrayList);
        recyclerView.setAdapter(adapter);

    }
}
