package com.example.boilerplate;

import android.view.View;

import com.example.boilerplate.ModelClasses.Directory;
import com.example.boilerplate.ModelClasses.SubDirectory;

import java.util.ArrayList;

public interface CustomDirectoryInterface {
    void onClicked(ArrayList<Directory> arrayList, Directory directory, int position, View view);
    void onClicked1(ArrayList<SubDirectory> arrayList, SubDirectory directory, int position, View view);
}
