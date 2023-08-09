package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class FruitletActivity extends AppCompatActivity {

    // Globals
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitlet);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.fruitlet_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Placeholder");
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

        // TODO: Update ListView with items in DB
        public void loadFruitletData() {
            DBOpener dbOpener = new DBOpener(this);
            db = dbOpener.getWritableDatabase();

            String[] varietyColumns = {DBOpener.VARIETY_NAME};
            // TODO: Adjust to reflect value retrieved from variety and date
            Cursor results = db.rawQuery("SELECT " + DBOpener.VARIETY_NAME + " FROM " + DBOpener.TABLE_VARIETY + ";", null);

            // TODO: Continue this method by loading DB data into the view
        }

        // TODO: Update ListView in real time (notifyDatasetChanged()) with items received from FruitletInput

        // TODO: Inflate FruitletInput layout into View fruitlet_view
        // https://stackoverflow.com/questions/9577304/how-can-you-make-a-custom-keyboard-in-android/45005691#45005691


    }
}