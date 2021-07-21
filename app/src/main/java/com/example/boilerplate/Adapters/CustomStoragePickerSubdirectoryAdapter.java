package com.example.boilerplate.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.CustomDirectoryInterface;
import com.example.boilerplate.ModelClasses.Directory;
import com.example.boilerplate.ModelClasses.SubDirectory;
import com.example.boilerplate.R;

import java.util.ArrayList;

public class CustomStoragePickerSubdirectoryAdapter extends RecyclerView.Adapter<CustomStoragePickerSubdirectoryAdapter.CustomViewHolder1> {
    Context context;
    ArrayList<SubDirectory> arrayList;
    CustomDirectoryInterface customDirectoryInterface;

    public CustomStoragePickerSubdirectoryAdapter(Context context, ArrayList<SubDirectory> arrayList, CustomDirectoryInterface customDirectoryInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.customDirectoryInterface = customDirectoryInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder1(LayoutInflater.from(context).inflate(R.layout.card_custom_storage_picker,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomStoragePickerSubdirectoryAdapter.CustomViewHolder1 holder, int position) {
        holder.tvDirectoryName.setText(arrayList.get(position).getDireName());
        holder.tvDirectoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked s","======>");
                customDirectoryInterface.onClicked1(arrayList,arrayList.get(position),position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder1 extends RecyclerView.ViewHolder {
        TextView tvDirectoryName;
        public CustomViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvDirectoryName = itemView.findViewById(R.id.tvDirectoryName);

        }
    }
}