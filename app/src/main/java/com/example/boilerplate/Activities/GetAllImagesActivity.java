package com.example.boilerplate.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.boilerplate.Adapters.AllImagesAdapter;
import com.example.boilerplate.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class GetAllImagesActivity extends AppCompatActivity {
    RecyclerView rvImages;
    AllImagesAdapter adapter;
    ArrayList<String> images;
    ArrayList<String> fileList = new ArrayList<>();
    String directory;
    ArrayList<String> directoryList = new ArrayList<>();
    Spinner spinnerDirectory;
    String selectedDirectory;
    ArrayAdapter adapterDirectory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_images);
        rvImages = findViewById(R.id.rvImages);
        spinnerDirectory = findViewById(R.id.spinnerDirectory);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }


//

        listDir();
        images = new ArrayList<>();

//
//        Toast.makeText(GetAllImagesActivity.this, "selected "+selectedDirectory, Toast.LENGTH_SHORT).show();

//        rvImages.setHasFixedSize(true);

//       findImage();
//
//        selectedDirectory = spinnerDirectory.getSelectedItem().toString();
//        Toast.makeText(this, selectedDirectory, Toast.LENGTH_SHORT).show();

    }

    private void listDir() {
        File filep = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        File[] files = filep.listFiles();
        fileList.clear();
        for (File file : files) {
            if (file.isDirectory() && !file.isHidden()) {
                File[] fileR = file.listFiles();
                for (File fileG : fileR){
                    if (fileG.getName().endsWith(".jpg")){
                        fileList.add(file.getName());
                        break;
                    }/*else if (fileG.isDirectory() &&!fileG.isHidden()){
                        File[] fileK = fileG.listFiles();
                        for (File fileM : fileK){
                            if (fileM.getName().endsWith(".jpg")){
                                fileList.add(fileG.getName());
                                break;
                            }
                        }
                    }*/
                }
            }
//             fileList.add(file.getName());
        }
        /*for (int i = 0; i < fileList.size(); i++) {
            Log.i(" " + (i + 1), ".  " + fileList.get(i));
            String paths[] = fileList.get(i).split("/",5);
            this.directory = "" + paths[paths.length-1];

            directoryList.add(this.directory);

        }*/

        for (int i = 0; i<fileList.size();i++){
            Log.e("directory List ","===>"+ fileList.get(i));
        }
        adapterDirectory = new ArrayAdapter<>(GetAllImagesActivity.this, android.R.layout.simple_spinner_item, fileList);
        adapterDirectory.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDirectory.setAdapter(adapterDirectory);
        spinnerDirectory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDirectoryP = parent.getSelectedItem().toString();
                Log.e("directroyyyy","====>"+selectedDirectoryP);
                File file = new File(Environment.getExternalStorageDirectory(),selectedDirectoryP);
                Log.e("directory path get ","====>"+selectedDirectoryP);

                images.clear();
                ArrayList<File> imageList = new ArrayList<>();
                File[] imageFile = file.listFiles();
                for (File files : imageFile) {
                    if (files.isDirectory() && !files.isHidden()) {
                        File[] fileQ = files.listFiles();
                        for (File fileo : fileQ){
                            if (fileo.getName().endsWith(".jpg")){
                                imageList.add(fileo);
                            }
                        }
                    } else {
                        if (files.getName().endsWith(".jpg")) {
                            imageList.add(files);
                        }
                    }
                }
                for (int i = 0; i < imageList.size(); i++) {
//            Log.i("imagePath","====>"+myFiles.get(i).getAbsolutePath());
                    images.add((imageList.get(i).getAbsolutePath()));

                }
                adapter = new AllImagesAdapter(GetAllImagesActivity.this, images);
                rvImages.setAdapter(adapter);
                rvImages.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

                Log.e("imageListSize", "====>" + imageList.size());
                Toast.makeText(GetAllImagesActivity.this, "imageListSize          " + imageList.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Toast.makeText(this,spinnerDirectory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    private void displayImages() {






    }

   /* private ArrayList<File> findImage(File filep) {
        ArrayList<File> imageList = new ArrayList<>();
        File[] files = filep.listFiles();
        fileList.clear();
        for (File file : files) {
            fileList.add(file.getPath());
        }
        for (int i = 0; i < fileList.size(); i++) {
            Log.i(" " + (i + 1), ".  " + fileList.get(i));
            String paths[] = fileList.get(i).split("/");
            directory = "" + paths[paths.length - 1];
            directoryList.add(directory);

        }
        spinnerDirectory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GetAllImagesActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                selectedDirectory  = parent.getSelectedItem().toString();
                for (File filej : files) {

                    if (filej.isDirectory() && !filej.isHidden()) {
                        Log.e("image  path  ", "====>" + filej.getAbsolutePath());
                        String imagePath[] = filej.getAbsolutePath().split("/");
                        imageDirectory = imagePath[4];
                        Log.e("image directory path  ", "====>" + imageDirectory);
                        Log.e("spinner selected item","========>"+selectedDirectory);
                        if (selectedDirectory == imageDirectory) {
                            Log.e("imagepath","==== directory path");
                            imageList.addAll(findImage(filej));
                        }
                    } else {
                        if (filej.getName().endsWith(".jpg")) {
                            String imagePath[] = filej.getAbsolutePath().split("/");
                            imageDirectory = imagePath[4];
                            if (selectedDirectory == imageDirectory) {
                                imageList.add(filej);
                            }

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return imageList;
    }*/

   /* private void findImage() {
        String directoryPath = listDir(directory);
        Log.e("directroyyyy","====>"+directoryPath);
        File file = new File(listDir(selectedDirectory));
        Log.e("directory path get ","====>"+listDir(selectedDirectory));

        ArrayList<File> imageList = new ArrayList<>();
        File[] imageFile = file.listFiles();
        for (File files : imageFile) {
            if (files.isDirectory() && !files.isHidden()) {
                imageList.addAll(Collections.singleton(files));
            } else {
                if (files.getName().endsWith(".jpg")) {
                    imageList.add(files);
                }
            }
        }
        for (int i = 0; i < imageList.size(); i++) {
//            Log.i("imagePath","====>"+myFiles.get(i).getAbsolutePath());
            images.add((imageList.get(i).getAbsolutePath()));

        }
        adapter = new AllImagesAdapter(GetAllImagesActivity.this, images);
        rvImages.setAdapter(adapter);
        rvImages.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        Log.e("imageListSize", "====>" + imageList.size());
        Toast.makeText(this, "imageListSize          " + imageList.size(), Toast.LENGTH_SHORT).show();
    }*/
}
