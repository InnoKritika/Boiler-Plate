package com.example.boilerplate.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boilerplate.Activities.SetWallPaperActivity;
import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;
import com.example.boilerplate.WallpaperInterface;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    Context context;
    ArrayList<Image> arrayList;
    WallpaperInterface wallpaperInterface;
    public ImageAdapter(Context context, ArrayList<Image> arrayList, WallpaperInterface wallpaperInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.wallpaperInterface=wallpaperInterface;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_list_stater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

       // Image image = new Image();


        Glide.with(context).load(arrayList.get(position).getImageUrl()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("image clicked","=====>"+arrayList.get(position));
//                Log.i("image clickedURL","=====>"+arrayList.get(position).getImageUrl());
                wallpaperInterface.wallpaperDetail(arrayList,v,position);
//                Log.i("objectUrl","====>"+arrayList.get(position).getImageUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageList);
        }
    }
}
