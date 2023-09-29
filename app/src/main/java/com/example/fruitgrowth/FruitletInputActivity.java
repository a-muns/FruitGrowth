package com.example.fruitgrowth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class FruitletInputActivity extends LinearLayout implements View.OnClickListener {

    // TODO: Finish at https://www.youtube.com/watch?v=iVhdelXbfrQ

    private Button button1, button2, button3, button4,
            button5, button6, button7, button8, button9,
            button0, buttonDot, buttonX, buttonEnter, buttonEnterClusters;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public FruitletInputActivity(Context context) {
        this(context, null, 0);
    }

    public FruitletInputActivity(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FruitletInputActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.activity_fruitlet_input, this, true);
        button1 = (Button) findViewById(R.id.fruitletInput_btn1);
        button2 = (Button) findViewById(R.id.fruitletInput_btn2);
        button3 = (Button) findViewById(R.id.fruitletInput_btn3);
        button4 = (Button) findViewById(R.id.fruitletInput_btn4);
        button5 = (Button) findViewById(R.id.fruitletInput_btn5);
        button6 = (Button) findViewById(R.id.fruitletInput_btn6);
        button7 = (Button) findViewById(R.id.fruitletInput_btn7);
        button8 = (Button) findViewById(R.id.fruitletInput_btn8);
        button9 = (Button) findViewById(R.id.fruitletInput_btn9);
        button0 = (Button) findViewById(R.id.fruitletInput_btn0);
        buttonDot = (Button) findViewById(R.id.fruitletInput_btnDot);
        buttonX = (Button) findViewById(R.id.fruitletInput_btnX);
        buttonEnter = (Button) findViewById(R.id.fruitletInput_btnEnter);
        buttonEnterClusters = (Button) findViewById(R.id.fruitletInput_btnEnterClusters);

        // On-click handler for each button
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonX.setOnClickListener(this);
        // TODO: Fix enters so they don't cause a crash; replace "this" with submission of data
        // and notifyDataSetChanged on fruitlet_listView
        buttonEnter.setOnClickListener(this);
        buttonEnterClusters.setOnClickListener(this);
//        Attempting to set intent on Enter Clusters button
//        buttonEnterClusters.setOnClickListener( click -> {
//            Intent newActivity = new Intent(this, ClusterActivity.class);
//            startActivity(newActivity);
//        }

        // Map each button to its value
        keyValues.put(R.id.fruitletInput_btn1, "1");
        keyValues.put(R.id.fruitletInput_btn2, "2");
        keyValues.put(R.id.fruitletInput_btn3, "3");
        keyValues.put(R.id.fruitletInput_btn4, "4");
        keyValues.put(R.id.fruitletInput_btn5, "5");
        keyValues.put(R.id.fruitletInput_btn6, "6");
        keyValues.put(R.id.fruitletInput_btn7, "7");
        keyValues.put(R.id.fruitletInput_btn8, "8");
        keyValues.put(R.id.fruitletInput_btn9, "9");
        keyValues.put(R.id.fruitletInput_btn0, "0");
        keyValues.put(R.id.fruitletInput_btnDot, ".");
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null) {
            return;
        }

        // Delete button functionality
        if (view.getId() == R.id.fruitletInput_btnX) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            // If field is empty, do nothing
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else { // Else delete and move back one space
                inputConnection.commitText("", 1);
            }
        } else { // Commit each button to the EditText as it's clicked
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value,1);
        }
    }

    // Set the inputConnection
    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}

// TODO: Insert values into treeCount, clusterCount, and fruitCount

// TODO: Enter and enterClusters update FruitletActivity with editText(with notifyDatasetChanged())
// check editText for allowable range

// TODO: Input button functionality