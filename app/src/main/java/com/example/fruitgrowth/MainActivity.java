package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start button redirect to VarietyActivity
        Button titleBtn = findViewById(R.id.main_startButton);
        titleBtn.setOnClickListener( click -> {
            Intent varietyIntent = new Intent(this, VarietyActivity.class);
            startActivity(varietyIntent);
        });
    }
}