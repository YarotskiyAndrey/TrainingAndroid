package com.example.kittenapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kittenapp.R;
import com.example.kittenapp.adapter.KittenAdapter;
import com.example.kittenapp.model.Kitten;
import com.example.kittenapp.model.KittenJson;
import com.example.kittenapp.network.KittenApi;
import com.example.kittenapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        mContext = this;
        setContentView(R.layout.activity_main);

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
            Log.d("MyAdd", "Calling generate()");
            generateKittenList(kittens);
        } else {
            Log.d("MyAdd", "Calling add()");
            adapter.addKittens(kittens);
            Log.d("MyAdd", "Added");
        }
    }

    private void generateKittenList(List<Kitten> kittenArrayList) {
        recyclerView = findViewById(R.id.kitten_list_view);
        adapter = new KittenAdapter(kittenArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Log.d("MyAdd", "Generated");
    }
}
