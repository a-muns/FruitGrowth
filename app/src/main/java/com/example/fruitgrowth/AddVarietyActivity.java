package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddVarietyActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_variety);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.addVariety_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.addVariety_toolbar);

        // Assign layout elements
        EditText addVarietyEditText = findViewById(R.id.addVariety_name);
        RadioGroup treeCountRadioGroup = findViewById(R.id.addVariety_chooseTrees);
        RadioGroup treeTypeRadioGroup = findViewById(R.id.addVariety_chooseType);
        Button addVarietyButton = findViewById(R.id.addVariety_saveButton);

        // Open Database
        DBOpener dbOpener = new DBOpener(this);
        db = dbOpener.getWritableDatabase();

        // Save variety to database and return to VarietyActivity
        addVarietyButton.setOnClickListener( click -> {

            // Get selected radio buttons
            int treeCountId = treeCountRadioGroup.getCheckedRadioButtonId();
            int treeTypeId = treeTypeRadioGroup.getCheckedRadioButtonId();
            RadioButton treeCountSelected = findViewById(treeCountId);
            RadioButton treeTypeSelected = findViewById(treeTypeId);

            // Collect and insert values into database
            ContentValues newRowValues = new ContentValues();
            newRowValues.put(DBOpener.VARIETY_NAME, addVarietyEditText.getText().toString());
            newRowValues.put(DBOpener.VARIETY_TREECOUNT, treeCountSelected.getText().toString());
            newRowValues.put(DBOpener.VARIETY_TREETYPE, treeTypeSelected.getText().toString());
            try {
                db.insertOrThrow(DBOpener.TABLE_VARIETY, null, newRowValues);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Return to VarietyActivity
            Intent varietyIntent = new Intent(this, VarietyActivity.class);
            startActivity(varietyIntent);
        });


    }
}