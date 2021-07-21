package com.example.boilerplate.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.Activities.ImageSubdirectoryActivity;
import com.example.boilerplate.CustomDirectoryInterface;
import com.example.boilerplate.ModelClasses.Directory;
import com.example.boilerplate.R;

import java.util.ArrayList;

public class CustomStoragePickerAdapter extends RecyclerView.Adapter<CustomStoragePickerAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Directory> arrayList;
    CustomDirectoryInterface customDirectoryInterface;

    public CustomStoragePickerAdapter(Context context, ArrayList<Directory> arrayList, CustomDirectoryInterface customDirectoryInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.customDirectoryInterface = customDirectoryInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.card_custom_storage_picker,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomStoragePickerAdapter.CustomViewHolder holder, int position) {
        holder.tvDirectoryName.setText(arrayList.get(position).getDirName());
        holder.tvDirectoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked","======>");
                customDirectoryInterface.onClicked(arrayList,arrayList.get(position),position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvDirectoryName;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDirectoryName = itemView.findViewById(R.id.tvDirectoryName);
        }
    }
}