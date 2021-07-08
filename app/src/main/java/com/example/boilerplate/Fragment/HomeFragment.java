package com.example.boilerplate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.boilerplate.Activities.LoginActivity;
import com.example.boilerplate.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    TextView name, email;
    ImageView ivUserImage;
    Button btnSignout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        name = view.findViewById(R.id.tvNameUser);
        email = view.findViewById(R.id.tvEmailUser);
        ivUserImage = view.findViewById(R.id.ivUserImage);
        btnSignout = view.findViewById(R.id.btnSignOut);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        name.setText(googleSignInAccount.getDisplayName());
        email.setText(googleSignInAccount.getEmail());
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).into(ivUserImage);


        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signout = new Intent(getContext(), LoginActivity.class);
                startActivity(signout);
            }
        });
        return view;
    }

}
