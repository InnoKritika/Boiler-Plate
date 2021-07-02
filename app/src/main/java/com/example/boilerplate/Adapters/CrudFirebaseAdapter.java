package com.example.boilerplate.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.boilerplate.ModelClasses.Crud;
import com.example.boilerplate.R;

import java.util.List;

public class CrudFirebaseAdapter extends ArrayAdapter<Crud> {

    private Activity context;
    private List<Crud> studentList;

    public CrudFirebaseAdapter (Activity context, List<Crud> studentList){
        super(context, R.layout.list_layout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater  =context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView tvName = listViewItem.findViewById(R.id.tvName);
        TextView tvSubject = listViewItem.findViewById(R.id.tvSubject);

        Crud crud = studentList.get(position);

        tvName.setText(crud.getName());
        tvSubject.setText(crud.getSubject());

        return listViewItem;
    }
}
