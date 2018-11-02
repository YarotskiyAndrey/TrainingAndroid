package ru.startandroid.training;

import android.content.Context;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    ImageView dayTimeImage;
    Button changeButton;
    TextView dayTimeText;

    String dayStateFileName = "dayStateFile.txt";

    String dayTimeString = "null";

    private static final String TAG = "myLogs";
    private static final String TAG_ONSAVE = "onSaveLogs";
    private static final String TAG_FILE = "FileLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG_ONSAVE, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayTimeImage = (ImageView) findViewById(R.id.dayTimeImage);
        changeButton = (Button) findViewById(R.id.changeButton);
        dayTimeText = (TextView) findViewById(R.id.dayTimeText);

        if (savedInstanceState != null) {
            Log.d(TAG_ONSAVE, savedInstanceState.getString("dayTime"));
        } else {
            Log.d(TAG_ONSAVE, "Failed");
        }


        View.OnClickListener onClickButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String morningText = getString(R.string.morning);
                String currentDayTimeText = dayTimeText.getText().toString();
                if (currentDayTimeText.equals(morningText)) {
                    Log.d(TAG, "Changing morning to night");

                    dayTimeImage.setImageResource(R.drawable.night);
                    dayTimeText.setText(R.string.night);
                } else {
                    Log.d(TAG, "Changing night to morning");

                    dayTimeImage.setImageResource(R.drawable.morning);
                    dayTimeText.setText(R.string.morning);
                }
            }
        };

        changeButton.setOnClickListener(onClickButtonListener);

    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("dayTime", dayTimeText.getText().toString());
        Log.d(TAG_ONSAVE, "onSaveInstanceState");
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d(TAG_ONSAVE, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_ONSAVE, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_ONSAVE, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_ONSAVE, "onDestroy");





    }

    @Override
    protected void onRestart() {
        Log.d(TAG_ONSAVE, "onRestart");
        super.onRestart();
    }


}