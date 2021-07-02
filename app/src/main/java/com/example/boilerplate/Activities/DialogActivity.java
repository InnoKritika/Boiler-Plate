package com.example.boilerplate.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boilerplate.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {
    Button btnAlertDialog,btnSimpleDialog,btnConfirmationDialog,btnFullScreenDialog;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnSimpleDialog = findViewById(R.id.btnSimpleDialog);
        btnConfirmationDialog = findViewById(R.id.btnConfirmationDialog);
        btnFullScreenDialog = findViewById(R.id.btnFullScreenDialog);

        final String[] items = {" PHP", " JAVA", " JSON", " C#", " Objective-C"};
        final ArrayList itemSelected = new ArrayList();

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(DialogActivity.this,R.style.AlertDialogTheme)
                        .setTitle("Title")
                        .setMessage("Sure Want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "No clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                alertDialog.getWindow().setAttributes(layoutParams);

            }
        });
        btnFullScreenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(DialogActivity.this,R.style.AlertDialogTheme)
                        .setTitle("Title")
                        .setMessage("Alert Dialog shows the Alert message and gives the answer in the form of yes or no. Alert Dialog displays the message to warn you and then according to your response the next step is processed.\n" +
                                "\n" +
                                "Android Alert Dialog is built with the use of three fields: Title, Message area, Action Button.\n" +
                                "Alert Dialog code has three methods:\n" +
                                "\n" +
                                "setTitle() method for displaying the Alert Dialog box Title\n" +
                                "setMessage() method for displaying the message\n" +
                                "setIcon() method is use to set the icon on Alert dialog box.\n" +
                                "Then we add the two Button, setPositiveButton and setNegativeButton to our Alert Dialog Box as shown below. to show a little text message to the users that clicks a button on my Android app, on IOS I just had to create an AlertView that it's simple to use but with Android i'm struggling because the solution seems x10 times harder. I saw that I need to use a DialogFragment but I can't understand how to make it work, can someone explain")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "No clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                alertDialog.getWindow().setAttributes(layoutParams);
                alertDialog.getWindow().setFlags(layoutParams.FLAG_FULLSCREEN,layoutParams.FLAG_FULLSCREEN);

            }
        });
        btnConfirmationDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);

                builder.setTitle("Select Languages you know : ");

                builder.setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int selectedItemId,
                                                boolean isSelected) {
                                if (isSelected) {

                                    itemSelected.add(selectedItemId);
                                } else if (itemSelected.contains(selectedItemId)) {

                                    itemSelected.remove(Integer.valueOf(selectedItemId));
                                }
                            }
                        })
                        .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                //Your logic when OK button is clicked
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                            }
                        });

                dialog = builder.create();

                dialog.show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);

            }
        });
        btnSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(DialogActivity.this,R.style.AlertDialogTheme)
                        .setTitle("Welcome to our Store")
                        .setMessage("")
                        .show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);

            }
        });
    }
}
