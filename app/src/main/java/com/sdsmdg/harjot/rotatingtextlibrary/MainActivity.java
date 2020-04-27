package com.sdsmdg.harjot.rotatingtextlibrary;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RotatingTextWrapper rotatingTextWrapper;
    Rotatable rotatable, rotatable2, rotatable3;
    Spinner s1;
    EditText e1;

    Button button;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/Reckoner_Bold.ttf");

        rotatingTextWrapper = findViewById(R.id.custom_switcher);
        rotatingTextWrapper.setSize(30);
        rotatingTextWrapper.setTypeface(typeface2);

        int[] colorArray = {Color.parseColor("#FFA036"), Color.parseColor("#100000"), Color.parseColor("#AAA136") };

//        rotatable = new Rotatable(Color.parseColor("#FFA036"), 1000, "Word00", "Word01", "Word02");
        rotatable = new Rotatable(colorArray, 1000, "rotating", "text", "library");
        rotatable.setSize(25);
        rotatable.setTypeface(typeface);
        rotatable.setInterpolator(new AccelerateInterpolator());
        rotatable.setAnimationDuration(500);

        Log.d(TAG, "onCreate: Rotatable 1 has been called!");

        rotatable2 = new Rotatable( 1000, "word", "word01", "word02");
        rotatable2.setSize(25);
        rotatable2.setTypeface(typeface);
        rotatable2.setInterpolator(new DecelerateInterpolator());
        rotatable2.setAnimationDuration(500);

        Log.d(TAG, "onCreate: Rotatable 2 has been called!");
        
//        rotatable3 = new Rotatable(Color.parseColor("#123456"), 1000, "4", "5", "6");
//        rotatable3.setSize(25);
//        rotatable3.setTypeface(typeface);
//        rotatable3.setInterpolator(new DecelerateInterpolator());
//        rotatable3.setAnimationDuration(500);

      //  rotatingTextWrapper.setContent("?abc ? abc", rotatable, rotatable2);
        rotatingTextWrapper.setContent("?abc ? abc", rotatable, rotatable2);
        Log.d(TAG, "onCreate: rot text wrapper set!");
//        rotatingTextWrapper.setContent("? abc", rotatable);

        s1 = (Spinner) findViewById(R.id.spinner);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(dataAdapter);
        s1.setSelection(0);

        e1 = (EditText) findViewById(R.id.addWord);

        button = findViewById(R.id.pause_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rotatingTextWrapper.getSwitcherList().get(0).isPaused()) {
                    rotatingTextWrapper.pause(0);
                } else {
                    rotatingTextWrapper.resume(0);
                }
            }
        });
    }


    public void addWord(View view) {
        String newWord = e1.getText().toString();
        if (TextUtils.isEmpty(newWord)) e1.setText("can't be left empty");
        else if (newWord.contains("\n")) e1.setText("one line only");
        else {
            rotatingTextWrapper.setAdaptable(true);
            rotatingTextWrapper.replaceWord(0, (int) s1.getSelectedItem() - 1, newWord);
        }
    }

}
