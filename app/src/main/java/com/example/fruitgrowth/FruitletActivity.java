package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class FruitletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitlet);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.fruitlet_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Placeholder");
        // TODO: Set toolbar text to variety name (using extra from VarietyActivity)
    }
}