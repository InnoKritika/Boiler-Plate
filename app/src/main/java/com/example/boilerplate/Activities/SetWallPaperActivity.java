package com.example.boilerplate.Activities;

import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.ModelClasses.Image;
import com.example.boilerplate.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SetWallPaperActivity extends AppCompatActivity {
    ImageView imageFull, image2;
    Button btnApply;
    TextView tv;
    StorageReference storageReference;
    Bitmap finalBitmap;
    Image image;
    String imageUrl;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashmap_firebase);

        imageFull = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView3);
        image2 = findViewById(R.id.image2);
        btnApply = findViewById(R.id.btnApply);

        image = (Image) getIntent().getSerializableExtra("image");


        imageUrl = image.getImageUrl();
        /*Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.image11)
                + '/' + getResources().getResourceTypeName(R.drawable.image11) + '/' + getResources().getResourceEntryName(R.drawable.image11) );

        Log.i("imageUir ","=====>"+imageUri);*/
        imageUri =  Uri.parse(imageUrl);


        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.get().load(R.drawable.ic_facebook)
                .error(R.drawable.ic_facebook)
                .into(image2);
//        Picasso.get().load(R.drawable.image14).into(imageFull);

        imageFull.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image2.setVisibility(View.INVISIBLE);
                        tv.setVisibility(View.INVISIBLE);
                        return true;

                    case MotionEvent.ACTION_UP:
                        image2.setVisibility(View.VISIBLE);
                        tv.setVisibility(View.VISIBLE);
                        return true;
                }
                return false;
            }
        });
        finalBitmap = bitmap;

        imageFull.setImageURI(null);
        imageFull.setImageURI(imageUri);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HashmapFirebaseActivity.this, "Apply Clicked", Toast.LENGTH_SHORT).show();
               setWallPaper();
            }
        });
    }

    public void setWallPaper(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SetWallPaperActivity.this,R.style.BottomsheetDialogTheme);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_wallpaper,
                (LinearLayout)findViewById(R.id.bottomSheetContainer));
        view.findViewById(R.id.tvLockScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(finalBitmap,null,true,WallpaperManager.FLAG_LOCK);
                        Log.i("changed","LockScreen====>");
                        bottomSheetDialog.dismiss();
                        Toast.makeText(SetWallPaperActivity.this, "Image set as wallpaper on lock screen", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("erroe","=======>"+e);
                }
            }
        });
        view.findViewById(R.id.tvHomeScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(finalBitmap);
                    Log.i("changed","HomeScreen====>");
                    bottomSheetDialog.dismiss();
                    Toast.makeText(SetWallPaperActivity.this, "Image set as wallpaper on lock screen", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("erroe","=======>"+e);
                }
            }
        });
        view.findViewById(R.id.tvBoth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(finalBitmap,null,true,WallpaperManager.FLAG_LOCK);
                        Log.i("changed","LockScreen====>");
                        wallpaperManager.setBitmap(finalBitmap);
                        Log.i("changed","HomeScreen====>");
                        bottomSheetDialog.dismiss();
                        Toast.makeText(SetWallPaperActivity.this, "Image set as wallpaper on Both screens", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("erroe","=======>"+e);
                }
            }
        });
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}


