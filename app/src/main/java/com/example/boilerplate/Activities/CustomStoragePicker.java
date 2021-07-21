package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.Adapters.CustomStoragePickerAdapter;
import com.example.boilerplate.CustomDirectoryInterface;
import com.example.boilerplate.ModelClasses.Directory;
import com.example.boilerplate.ModelClasses.SubDirectory;
import com.example.boilerplate.R;

import java.io.File;
import java.util.ArrayList;

public class CustomStoragePicker extends AppCompatActivity implements CustomDirectoryInterface {
    RecyclerView rvDirectoryList;
    ArrayList<Directory> fileList;
    ArrayList<SubDirectory> directoryList;
    CustomStoragePickerAdapter adapter;
    CustomDirectoryInterface customDirectoryInterface = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_storage_picker);

        rvDirectoryList = findViewById(R.id.rvDirectoryList);
        fileList = new ArrayList<>();
        directoryList = new ArrayList<>();
        listDir();
    }

    private void listDir() {
        File filep = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        File[] files = filep.listFiles();
        fileList.clear();
        for (File file : files) {
            if (file.isDirectory() && !file.isHidden()) {
                String[] path = file.getAbsolutePath().split("/", 5);
                String dirName = path[path.length - 1];
                fileList.add(new Directory(dirName));
            }
        }
        adapter = new CustomStoragePickerAdapter(getApplicationContext(), fileList,customDirectoryInterface);
        rvDirectoryList.setAdapter(adapter);
        rvDirectoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        for (int i = 0; i < fileList.size(); i++) {
//            Log.e("directory List ", "===>" + fileList.get(i).getDirName());
            File fileQ = new File(Environment.getExternalStorageDirectory(), "/"+fileList.get(i).getDirName());
//            Log.e("directory path","=======>"+fileQ);
            File[] fileI = fileQ.listFiles();
            directoryList.clear();
            for (File file : fileI) {
//            Log.e("path","======>"+file.getName());
                if (file.isDirectory() && !file.isHidden()) {
//                Log.e("path1","======>"+file.getName());
                    String[] path = file.getAbsolutePath().split("/", 6);
                    String dirName = path[path.length - 1];
                    Log.e("path name","=======>"+dirName);
                    directoryList.add(new SubDirectory(dirName));
                    Log.e("List size===="+fileList.get(i).getDirName(),"=======>"+directoryList.size());
                }
            }
        }




        /*for (int i = 0; i < directoryList.size(); i++) {
            Log.e("directory List ", "===>" + directoryList.get(i).getDireName());
        }*/
    }

    @Override
    public void onClicked(ArrayList<Directory> arrayList, Directory directory, int position, View view) {
        Log.e("clicked activity","========.");
        Intent in = new Intent(CustomStoragePicker.this,ImageSubdirectoryActivity.class);
        in.putExtra("arrayList",arrayList);
        in.putExtra("position",""+position);
        startActivity(in);

    }

    @Override
    public void onClicked1(ArrayList<SubDirectory> arrayList, SubDirectory directory, int position, View view) {

    }
}
