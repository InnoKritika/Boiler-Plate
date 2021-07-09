package com.example.boilerplate.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.boilerplate.Adapters.ImageAdapter;
import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;
import com.example.boilerplate.WallpaperInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetWallPaperActivity extends AppCompatActivity implements WallpaperInterface, Serializable {

    RecyclerView rv;
    DatabaseReference reference;
    ImageAdapter adapter;
    ProgressDialog progressDialog;
    ArrayList<Image> images ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_firebase);

        rv=findViewById(R.id.rvWallPaper);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        images = new ArrayList<>();
        progressDialog = new ProgressDialog(GetWallPaperActivity.this);
        progressDialog.setMessage("Images are loading");
        progressDialog.show();

        adapter = new ImageAdapter(getApplicationContext(),images,this::wallpaperDetail);
        rv.setAdapter(adapter);
        reference= FirebaseDatabase.getInstance().getReference("images");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
//                    Log.i("data","==========");
                    Image image = data.getValue(Image.class);
//                    Log.i("datarecieved","=======>"+image);
                    images.add(image);

                }
//                Log.i("datarecieved","=======>"+images);
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error","=======>"+error);
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public void wallpaperDetail(Image image1, View view, int position) {
        Intent detail = new Intent(GetWallPaperActivity.this,SetWallPaperActivity.class);
        detail.putExtra("image", image1);
        Log.i("imageObjectSend","=====>"+image1);
        Log.i("imageUrlSend","=====>"+image1.getImageUrl());
        startActivity(detail);
    }
}
