package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.boilerplate.R;
import com.google.android.material.card.MaterialCardView;

public class CardViewActivity extends AppCompatActivity {

    CardView expandCard;
    LinearLayout hiddenView;
    ImageView arrow;
    Button swapCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_card_view);

        expandCard = findViewById(R.id.expandableCardView);
        hiddenView = findViewById(R.id.hiddenView);
        arrow = findViewById(R.id.arrowDown);
        swapCard = findViewById(R.id.swapCard);

        swapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swap = new Intent(CardViewActivity.this,SwapCardViewActivity.class);
                startActivity(swap);
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenView.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(expandCard,new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.down_arrow);
                }
                else {
                    TransitionManager.beginDelayedTransition(expandCard,new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.up_arrow);
                }
            }
        });
    }
}
