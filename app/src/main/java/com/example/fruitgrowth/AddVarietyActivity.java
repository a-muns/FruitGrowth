package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddVarietyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_variety);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.addVariety_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addVariety_toolbar);


    }
}