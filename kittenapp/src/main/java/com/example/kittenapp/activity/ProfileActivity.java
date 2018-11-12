package com.example.kittenapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.kittenapp.R;

import java.util.concurrent.TimeUnit;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.textView);
        String kittenName = "default";
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            kittenName = extras.getString("name");
        }
        textView.setText(kittenName);
    }
}
