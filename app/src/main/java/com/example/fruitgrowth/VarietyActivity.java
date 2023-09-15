package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class VarietyActivity extends AppCompatActivity {

    /**
     * Globals
     */
    private ArrayList<String> varietyArray = new ArrayList<>();
    private ArrayAdapter<String> varietyAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variety);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.variety_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.variety_toolbar);

        // TODO: Populate ListView from DB
        loadVarietyData();
        ListView varietyListView = findViewById(R.id.variety_ListView);
        varietyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, varietyArray);
        varietyListView.setAdapter(varietyAdapter);

        // TODO: Test onClick any ListView Item
        varietyListView.setOnItemClickListener((list, item, position, id) -> {
            String varietyName = varietyArray.get(position);
            System.out.print(varietyName);

            // Place extra into an intent to FruitletActivity
            Intent newActivity = new Intent(this, FruitletActivity.class);
            newActivity.putExtra("varietyName", varietyName);
            startActivity(newActivity);
        });

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
            Intent manageIntent = new Intent(this, FruitletActivity.class);
            startActivity(manageIntent);
        });

    }

    /**
     * Load variety names into ListView
     */
    public void loadVarietyData() {
        DBOpener dbOpener = new DBOpener(this);
        db = dbOpener.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT " + DBOpener.VARIETY_NAME + " FROM " + DBOpener.TABLE_VARIETY + ";", null);

        while (results.moveToNext()) {
            try {
                String name = results.getString(0);
                varietyArray.add(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}