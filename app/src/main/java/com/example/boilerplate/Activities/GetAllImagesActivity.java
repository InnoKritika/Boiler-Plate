package com.example.boilerplate.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.boilerplate.Adapters.AllImagesAdapter;
import com.example.boilerplate.R;

import java.io.File;
import java.util.ArrayList;

public class GetAllImagesActivity extends AppCompatActivity {
    RecyclerView rvImages;
    ArrayList<File> myFiles;
    AllImagesAdapter adapter;
    ArrayList<String> images;
    ArrayList<String> fileList = new ArrayList<>();
    String directory;
    ArrayList<String> directoryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_images);
        rvImages = findViewById(R.id.rvImages);

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        listDir(root);

        myFiles = new ArrayList<>();
        images = new ArrayList<>();


//        rvImages.setHasFixedSize(true);

//        displayImages();


    }

    private void listDir(File root) {

        File[] files = root.listFiles();
        fileList.clear();
        for (File file : files){
            fileList.add(file.getPath());
        }
        for (int i=0; i<fileList.size(); i++){
            Log.i(" "+(i+1),".  "+fileList.get(i));
            String paths[] = fileList.get(i).split("/");
            directory = ""+(paths.length-1);
            directoryList.add(directory);

        }
        adapter = new AllImagesAdapter(GetAllImagesActivity.this, directoryList);
        rvImages.setAdapter(adapter);
        rvImages.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

    }

   /* private void displayImages() {


        myFiles = findImage(Environment.getExternalStorageDirectory());
        Log.i("imageListSize", "====>" + myFiles.size());
        Toast.makeText(this, "imageListSize          "+myFiles.size(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < myFiles.size(); i++) {
            images.add((myFiles.get(i).getAbsolutePath()));

        }

    }

    private ArrayList<File> findImage(File file) {

        ArrayList<File> imageList = new ArrayList<>();
        File[] imageFile = file.listFiles();
        for (File files : imageFile) {
            if (files.isDirectory() && !files.isHidden()) {

                imageList.addAll(findImage(files));
            } else {
                if (files.getName().endsWith(".jpg")) {
                    imageList.add(files);
                }
            }
        }
        return imageList;
    }*/
}
