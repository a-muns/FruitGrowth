package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class FruitletActivity extends AppCompatActivity {

    // Globals
    SQLiteDatabase db;
    String varietyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitlet);

        Intent intentReceived= getIntent();
        varietyName = intentReceived.getStringExtra("varietyName");

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.fruitlet_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(varietyName);
            // TODO: Set toolbar text to variety name (using extra from VarietyActivity)


        // TODO: Correctly set keyboard to the desired EditText (onClickListener?)
        EditText currentEdit = findViewById(R.id.testEdit);

        FruitletInputActivity fruitletKeyboard = findViewById(R.id.fruitletKeyboard);
        currentEdit.setRawInputType(InputType.TYPE_CLASS_TEXT);
        currentEdit.setTextIsSelectable(true);

        // Set InputConnection
        InputConnection ic = currentEdit.onCreateInputConnection(new EditorInfo());
        fruitletKeyboard.setInputConnection(ic);

        // TODO: Find a way to include an edit button in each ListView item


        loadFruitletData();


        // TODO: Update ListView in real time (notifyDatasetChanged()) with items received from FruitletInput

        // TODO: Inflate FruitletInput layout into View fruitlet_view
        // https://stackoverflow.com/questions/9577304/how-can-you-make-a-custom-keyboard-in-android/45005691#45005691


    }

    // TODO: Update ListView with items in DB
    public void loadFruitletData() {
        DBOpener dbOpener = new DBOpener(this);
        db = dbOpener.getWritableDatabase();

        String[] varietyColumns = {DBOpener.VARIETY_NAME};


        Cursor varietyResults = db.rawQuery("SELECT " + DBOpener.VARIETY_NAME + ", " + DBOpener.VARIETY_TREECOUNT +
                " FROM " + DBOpener.TABLE_VARIETY + ";", null);
        Cursor treeResults = db.rawQuery("SELECT " + DBOpener.TREE_DATE + " FROM " + DBOpener.TABLE_TREE + ";", null);

        // TODO: Get correct values to populate from  (create classes to represent Fruitlet, Tree, Variety etc.?)
        int varietyIndex = varietyResults.getColumnIndex(DBOpener.VARIETY_NAME);
        int treeCountIndex = varietyResults.getColumnIndex(DBOpener.VARIETY_TREECOUNT);
        String varietyName = "";
        int treeCount = 999;
        String treeDate = "";

        while (varietyResults.moveToNext()) {
            try {
                varietyName = varietyResults.getString(varietyIndex);
                treeCount = varietyResults.getInt(treeCountIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // TODO: Convert date from Tree table into date format (or leave as string?)
//        try {
//            treeDate = treeResults.getString(0);
//        } catch (Exception e) {
//                e.printStackTrace();
//        }

        // Initialize TextViews as variables
        TextView treeCountTextView = findViewById(R.id.fruitlet_treesCount);
        TextView dateTextView = findViewById(R.id.fruitlet_date);

        // Set TextViews to data retrieved from DB
        treeCountTextView.setText((String.valueOf(treeCount) + " Trees"));
        dateTextView.setText(treeDate);



        // TODO: Continue this method by loading DB data into the view
    }
}