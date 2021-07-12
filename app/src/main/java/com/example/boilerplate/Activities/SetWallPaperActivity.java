package com.example.boilerplate.Activities;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SetWallPaperActivity extends AppCompatActivity {
    ImageView imageFull, image2;
    Button btnApply;
    TextView tv;
    ProgressDialog progressDialog;
    Image image;
    String imageUrl,imageUrlLeft,imageUrlRight,pos;
    ArrayList<Image> images;
    SwipeListener swipeListener;
    RelativeLayout relativeLayout;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashmap_firebase);

        imageFull = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView3);
        image2 = findViewById(R.id.image2);
        btnApply = findViewById(R.id.btnApply);
        relativeLayout = findViewById(R.id.relativeLayout);
        progressDialog = new ProgressDialog(SetWallPaperActivity.this);

        images = (ArrayList<Image>) getIntent().getSerializableExtra("imageList");
        pos = (String) getIntent().getSerializableExtra("imagePosition");

        position = Integer.parseInt(pos);
        Log.i("position","======>"+position);

        imageUrl = images.get(position).getImageUrl();

        Glide.with(SetWallPaperActivity.this).load(imageUrl).into(imageFull);

        imageFull.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("","action down");
                        image2.setVisibility(View.INVISIBLE);
                        tv.setVisibility(View.INVISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                        Log.i("","action down");
                        image2.setVisibility(View.VISIBLE);
                        tv.setVisibility(View.VISIBLE);
                        return true;

                    case MotionEvent.EDGE_LEFT:
                        Glide.with(SetWallPaperActivity.this).load(images.get(position-1).getImageUrl()).into(imageFull);
                        return true;
                }
                return false;
            }
        });

        swipeListener = new SwipeListener(relativeLayout);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetWallPaperActivity.this, "Apply Clicked", Toast.LENGTH_SHORT).show();
                setWallPaper();

            }
        });
    }

    public void setWallPaper() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SetWallPaperActivity.this, R.style.BottomsheetDialogTheme);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_wallpaper,
                (LinearLayout) findViewById(R.id.bottomSheetContainer));
        view.findViewById(R.id.tvLockScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Setting Wallpaper on lock screen ");
                progressDialog.show();
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(SetWallPaperActivity.this);
                Glide.with(SetWallPaperActivity.this)
                        .asBitmap().load(imageUrl)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                progressDialog.dismiss();
                                bottomSheetDialog.dismiss();
                                Toast.makeText(SetWallPaperActivity.this, "====failed to load image====", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                                try {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_LOCK);
                                        progressDialog.dismiss();
                                        bottomSheetDialog.dismiss();
                                        Toast.makeText(SetWallPaperActivity.this, "WallPaper set to Lock screen", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.e("Failed to set", "==========>" + e);
                                }
                                return false;
                            }
                        }).submit();
            }
        });
        view.findViewById(R.id.tvHomeScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Setting Wallpaper on Home screen ");
                progressDialog.show();
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(SetWallPaperActivity.this);
                Glide.with(SetWallPaperActivity.this)
                        .asBitmap().load(imageUrl)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                Toast.makeText(SetWallPaperActivity.this, "====failed to load image====", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                progressDialog.dismiss();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                                try {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        wallpaperManager.setBitmap(resource);
                                        progressDialog.dismiss();
                                        bottomSheetDialog.dismiss();
                                        Toast.makeText(SetWallPaperActivity.this, "WallPaper set to Home screen", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.e("Failed to set", "==========>" + e);
                                }
                                return false;
                            }
                        }).submit();
            }
        });
        view.findViewById(R.id.tvBoth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Setting Wallpaper on Both screens ");
                progressDialog.show();
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(SetWallPaperActivity.this);
                Glide.with(SetWallPaperActivity.this)
                        .asBitmap().load(imageUrl)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                progressDialog.dismiss();
                                bottomSheetDialog.dismiss();
                                Toast.makeText(SetWallPaperActivity.this, "====failed to load image====", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                                try {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        wallpaperManager.setBitmap(resource);
                                        wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_LOCK);
                                        progressDialog.dismiss();
                                        bottomSheetDialog.dismiss();
                                        Toast.makeText(SetWallPaperActivity.this, "WallPaper set to Home screen", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.e("Failed to set", "==========>" + e);
                                }
                                return false;
                            }
                        }).submit();
            }
        });
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();
                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                            if (xDiff > 0) {
                                //swipeRight
                                Log.i("Right swipped","========>");
                                Glide.with(SetWallPaperActivity.this).load(R.drawable.image11).into(imageFull);
                            } else {
                                Log.i("Right swipped","========>");
                                Glide.with(SetWallPaperActivity.this).load(R.drawable.image16).into(imageFull);
                            }
                            return true;
                        }
                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(simpleOnGestureListener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}


