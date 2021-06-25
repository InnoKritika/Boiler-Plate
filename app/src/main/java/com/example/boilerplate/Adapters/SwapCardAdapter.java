package com.example.boilerplate.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boilerplate.ModelClasses.Card;
import com.example.boilerplate.R;

import java.util.ArrayList;

public class SwapCardAdapter extends RecyclerView.Adapter<SwapCardAdapter.CardViewHolder> {
    Context context;
    ArrayList<Card> arrayList;

    public SwapCardAdapter(Context context, ArrayList<Card> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageList);
        }
    }
}
