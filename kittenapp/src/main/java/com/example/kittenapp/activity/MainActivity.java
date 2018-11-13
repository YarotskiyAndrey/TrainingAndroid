package com.example.kittenapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.kittenapp.R;
import com.example.kittenapp.adapter.KittenAdapter;
import com.example.kittenapp.adapter.ItemClickListener;
import com.example.kittenapp.decoration.SimpleDividerItemDecoration;
import com.example.kittenapp.model.Kitten;
import com.example.kittenapp.model.KittenJson;
import com.example.kittenapp.network.KittenApi;
import com.example.kittenapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private static Context mContext;

    private RecyclerView recyclerView;
    private KittenAdapter adapter;
    private KittenApi kittenApi;

    private ImageButton addKittensButton;

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        kittenApi = RetrofitInstance.getRetrofitInstance().create(KittenApi.class);

        callNewKittens();

        addKittensButton = findViewById(R.id.add_kittens_button);
        addKittensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNewKittens();
            }
        });
    }

    private void callNewKittens() {
        Call<List<KittenJson>> call = kittenApi.getImageUrl("json", "jpg", 4);

        call.enqueue(new Callback<List<KittenJson>>() {
                         @Override
                         public void onResponse(Call<List<KittenJson>> call, Response<List<KittenJson>> response) {
                             createKittens(response.body());
                         }

                         @Override
                         public void onFailure(Call<List<KittenJson>> call, Throwable t) {
                             t.printStackTrace();
                         }
                     }
        );
    }

    private void createKittens(List<KittenJson> kittenJsonList) {
        List<Kitten> kittens = new ArrayList<>();

        for (KittenJson kittenJson : kittenJsonList
                ) {
            kittens.add(new Kitten(kittenJson));
        }
        if (adapter == null) {
            generateKittenList(kittens);
        } else {
            adapter.addKittens(kittens);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }
    }

    private void generateKittenList(List<Kitten> kittenArrayList) {
        recyclerView = findViewById(R.id.kitten_list_view);
        adapter = new KittenAdapter(kittenArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        final Kitten kitten = adapter.getKitten(position);
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("imageUrl", kitten.getImageUrl());
        i.putExtra("phone", kitten.getPhone());
        i.putExtra("number", kitten.getNumber());
        i.putExtra("name", kitten.getName());
        i.putExtra("kitten", kitten);
        startActivity(i);
    }
}
