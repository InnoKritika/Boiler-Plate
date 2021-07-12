package com.example.boilerplate;

import android.view.View;

import com.example.boilerplate.ModelClasses.Image;

import java.util.ArrayList;

public interface WallpaperInterface {
     void wallpaperDetail(ArrayList<Image> images, View view, int position);
}
