package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class VarietyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variety);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.variety_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.variety_toolbar);

        // TODO: Inflate listview from DB

        // TODO: Set listview onclick to startActivity(fruitletActivity.class) plus extras

        // Set buttons' on-clicks
        Button addVarietyBtn = findViewById(R.id.variety_btnAddVariety);
        Button manageFilesBtn = findViewById(R.id.variety_btnManageFiles);

            // Add Variety
        addVarietyBtn.setOnClickListener( click -> {
            Intent addVarietyIntent = new Intent(this, AddVarietyActivity.class);
            startActivity(addVarietyIntent);
        });

            // Manage Files
        manageFilesBtn.setOnClickListener( click -> {
            Intent manageIntent = new Intent(this, ManageActivity.class);
            startActivity(manageIntent);
        });



    }
}