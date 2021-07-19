package com.example.boilerplate.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boilerplate.Activities.FullImageActivity;
import com.example.boilerplate.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;

public class AllImagesAdapter extends RecyclerView.Adapter<AllImagesAdapter.AllImagesViewholder> {
    Context context;
    ArrayList<String> fileArrayList;


    public AllImagesAdapter(Context context, ArrayList<String> fileArrayList) {
        this.context = context;
        this.fileArrayList = fileArrayList;
    }

    @NonNull
    @Override
    public AllImagesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllImagesViewholder(LayoutInflater.from(context).inflate(R.layout.image_item_list_stater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllImagesAdapter.AllImagesViewholder holder, int position) {
//        holder.tv.setText(fileArrayList.get(position));
//        Glide.with(context).load(fileArrayList.get(position))
        Glide.with(context).load(fileArrayList.get(position)).placeholder(R.drawable.ic_launcher_foreground).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, FullImageActivity.class);
//                intent.putExtra("fileAbsolutePath",fileArrayList.get(position).getAbsolutePath());
                Log.i("fileAbsolutePath", "==========>" + fileArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileArrayList.size();
    }

    public class AllImagesViewholder extends RecyclerView.ViewHolder {
        //        TextView tv;
        RoundedImageView image;

        public AllImagesViewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageList);
//            tv = itemView.findViewById(R.id.tvDirectory);
//        }
        }
//    public interface onPhotoListener{
//        void onPhotoClicked(String path);
//    }
    }
}
