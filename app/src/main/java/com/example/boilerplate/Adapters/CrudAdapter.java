package com.example.boilerplate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boilerplate.ModelClasses.User;
import com.example.boilerplate.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CrudAdapter extends RecyclerView.Adapter<CrudAdapter.CrudViewHolder> {

    Context context;
    ArrayList<User> arrayList;

    public CrudAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CrudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrudViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_operation_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrudViewHolder holder, final int position) {
        final int itemPos = position;
        final User user = arrayList.get(position);
        holder.name.setText(user.getFullName());
        holder.username.setText(user.getUsername());
        holder.number.setText(user.getPhoneNumber());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CrudViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, username, number;
        Button edit, delete;

        public CrudViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            email = itemView.findViewById(R.id.tv_email);
            number = itemView.findViewById(R.id.tv_phone);
            username = itemView.findViewById(R.id.tv_username);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
