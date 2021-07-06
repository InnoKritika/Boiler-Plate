package com.example.boilerplate.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.codekidlabs.storagechooser.StorageChooser;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.example.boilerplate.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.hasPermissions;

public class DynamicStorageLocationActivity extends AppCompatActivity {
    private static final int FOLDERPICKER_PERMISSIONS = 1;
    Button btnDownload, btnStorageLocation;
    ImageView image;
    private String url;
    String filePathUser;
    TextView tvPath;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_storage);

        image = findViewById(R.id.image_view);
        btnDownload = findViewById(R.id.btnDownload);
        btnStorageLocation = findViewById(R.id.btnStorageLocation);
        tvPath = findViewById(R.id.tvPath);


        PRDownloader.initialize(getApplicationContext());

        url = "https://images6.fanpop.com/image/photos/42700000/Butterfly-s-butterflies-42798715-564-1003.jpg";

        Picasso.get().load(url).into(image);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        btnStorageLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] PERMISSIONS = {
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if(hasPermissions(DynamicStorageLocationActivity.this, PERMISSIONS)){
                    ShowDirectoryPicker();
                }else{
                    ActivityCompat.requestPermissions(DynamicStorageLocationActivity.this, PERMISSIONS, FOLDERPICKER_PERMISSIONS);
                }
            }
        });
    }

    private boolean hasPermissions(Context context, String[] permissions) {

        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void ShowDirectoryPicker() {

        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(DynamicStorageLocationActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.DIRECTORY_CHOOSER)
                .build();

        // 2. Retrieve the selected path by the user and show in a toast !
        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                Toast.makeText(DynamicStorageLocationActivity.this, "The selected path is ===> " + path, Toast.LENGTH_SHORT).show();
                tvPath.setText(path);
                filePathUser = path;
            }
        });

        // 3. Display File Picker !
        chooser.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case FOLDERPICKER_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                            DynamicStorageLocationActivity.this,
                            "Permission granted! Please click on pick a file once again.",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            DynamicStorageLocationActivity.this,
                            "Permission denied to read your External storage :(",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                return;
            }
        }
    }



    private void checkPermissions(){
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()){
                    downloadImage();
                }else {
                    Toast.makeText(DynamicStorageLocationActivity.this, "Allow permissions", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }

        }).check();
    }

    private void downloadImage() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Downloading...");
        pd.setCancelable(false);
        pd.show();

        File filePath = new File(filePathUser);
        Log.i("Absolute path","===>"+filePath.getAbsolutePath());
        if (filePath.exists()) {
            PRDownloader.download(url, filePath.getAbsolutePath(), URLUtil.guessFileName(url, null, null))
                    .build()
                    .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                        @Override
                        public void onStartOrResume() {

                        }
                    })
                    .setOnPauseListener(new OnPauseListener() {
                        @Override
                        public void onPause() {

                        }
                    })
                    .setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel() {

                        }
                    })
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(Progress progress) {

                            Long percent = progress.currentBytes * 100 / progress.totalBytes;
                            pd.setMessage("Downloaded : " + percent + "%");

                        }
                    })
                    .start(new OnDownloadListener() {
                        @Override
                        public void onDownloadComplete() {
                            Toast.makeText(DynamicStorageLocationActivity.this, "Downloading Complete ====>   " + filePath, Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            Log.i("location", "===>" + filePath.getPath());
                            Toast.makeText(DynamicStorageLocationActivity.this, "File path ===> " + filePathUser, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(Error error) {
                            Toast.makeText(DynamicStorageLocationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            Log.e("error", "==>" + error);
                            pd.dismiss();
                        }

                    });
        }else {
            Toast.makeText(this, "not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
