package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.Adapters.CustomStoragePickerSubdirectoryAdapter;
import com.example.boilerplate.CustomDirectoryInterface;
import com.example.boilerplate.ModelClasses.Directory;
import com.example.boilerplate.ModelClasses.SubDirectory;
import com.example.boilerplate.R;

import java.io.File;
import java.util.ArrayList;

public class ImageSubdirectoryActivity extends AppCompatActivity implements CustomDirectoryInterface {
    Directory directory;
    ArrayList<Directory> directoryArrayList;
    ArrayList<SubDirectory> subDirectoryArrayList;
    int position1;
    String pos,directoryPath;
    RecyclerView rvSubDirectory;
    CustomStoragePickerSubdirectoryAdapter adapter;
    CustomDirectoryInterface customDirectoryInterface = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_sub_directory);

        directoryArrayList = (ArrayList<Directory>) getIntent().getSerializableExtra("arrayList");
        pos = (String) getIntent().getSerializableExtra("position");
        position1 =Integer.parseInt(pos);


        directoryPath = directoryArrayList.get(position1).getDirName();
        rvSubDirectory = findViewById(R.id.rvSubDirectory);

        subDirectoryArrayList = new ArrayList<>();

        File fileQ = new File(Environment.getExternalStorageDirectory(), "/"+directoryArrayList.get(position1).getDirName());
//            Log.e("directory path","=======>"+fileQ);
        File[] fileI = fileQ.listFiles();
        subDirectoryArrayList.clear();
        for (File file : fileI) {
//            Log.e("path","======>"+file.getName());
            if (file.isDirectory() && !file.isHidden()) {
//                Log.e("path1","======>"+file.getName());
                String[] path = file.getAbsolutePath().split("/", 6);
                String dirName = path[path.length - 1];
                Log.e("path name","=======>"+dirName);
                subDirectoryArrayList.add(new SubDirectory(dirName));
                Log.e("List size===="+directoryArrayList.get(position1).getDirName(),"=======>"+subDirectoryArrayList.size());
            }
        }
        adapter = new CustomStoragePickerSubdirectoryAdapter(getApplicationContext(),subDirectoryArrayList,customDirectoryInterface);
        rvSubDirectory.setAdapter(adapter);
        rvSubDirectory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }


    @Override
    public void onClicked(ArrayList<Directory> arrayList, Directory directory, int position, View view) {
        Toast.makeText(this, "/"+directoryPath+"/"+subDirectoryArrayList.get(position).getDireName(), Toast.LENGTH_SHORT).show();
        Log.e("file path","======>"+"/"+directoryPath+"/"+subDirectoryArrayList.get(position).getDireName());
    }

    @Override
    public void onClicked1(ArrayList<SubDirectory> arrayList, SubDirectory directory, int position, View view) {

    }
}
