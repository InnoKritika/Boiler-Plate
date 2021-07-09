package com.example.boilerplate.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.boilerplate.Adapters.ImageAdapter;
import com.example.boilerplate.ModelClasses.Card;
import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SwapCardViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter adapter;
    ArrayList<Image> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view);
        /*recyclerView = findViewById(R.id.rvCardView);
        arrayList  =new ArrayList<>();

        arrayList.add(new Image(R.drawable.image10));
        arrayList.add(new Image(R.drawable.image11));
        arrayList.add(new Image(R.drawable.image12));
        arrayList.add(new Image(R.drawable.image13));
        arrayList.add(new Image(R.drawable.image14));
        arrayList.add(new Image(R.drawable.image15));
        arrayList.add(new Image(R.drawable.image16));
        arrayList.add(new Image(R.drawable.image17));
        arrayList.add(new Image(R.drawable.image18));
        arrayList.add(new Image(R.drawable.image19));
        arrayList.add(new Image(R.drawable.image20));

        recyclerView.setLayoutManager(new GridLayoutManager(SwapCardViewActivity.this,2));
        adapter = new ImageAdapter(getApplicationContext(),arrayList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);*/

    }

    /*Image deletedItem = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback( ItemTouchHelper.UP |ItemTouchHelper.DOWN
    | ItemTouchHelper.START|ItemTouchHelper.END|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(arrayList,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            switch (direction){
                case ItemTouchHelper.LEFT:
                    deletedItem = arrayList.get(position);
                    arrayList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, (CharSequence) deletedItem,Snackbar.LENGTH_LONG).
                            setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    arrayList.add(position,deletedItem);
                                    adapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    break;
            }

        }
    };*/
}
