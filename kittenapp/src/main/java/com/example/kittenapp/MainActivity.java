package com.example.kittenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView kittenListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void initRecyclerView(){
        kittenListView = findViewById(R.id.kitten_list_view);

        kittenListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
