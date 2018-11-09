package com.example.kittenapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private RecyclerView recyclerView;
    private KittenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KittenApi kittenApi = RetrofitInstance.getRetrofitInstance().create(KittenApi.class);

        Call<List<KittenJson>> call = kittenApi.getImageUrl("json", "jpg", 4);


        call.enqueue(new Callback<List<KittenJson>>() {
                         @Override
                         public void onResponse(Call<List<KittenJson>> call, Response<List<KittenJson>> response) {
                             ArrayList<Kitten> kittenArrayList = createKittens(response.body());

                             generateKittenList(kittenArrayList);
                         }

                         @Override
                         public void onFailure(Call<List<KittenJson>> call, Throwable t) {
                             t.printStackTrace();
                         }
                     }
        );

        Log.d("MyDebug","endCall ");

    }

    private ArrayList<Kitten> createKittens(List<KittenJson> kittenJsonList) {
        Log.d("MyDebug","createKittens");
        ArrayList<Kitten> kittens = new ArrayList<>();

        for (KittenJson kittenJson : kittenJsonList
                ) {
            kittens.add(new Kitten(kittenJson));
        }
        return kittens;
    }

    private void generateKittenList(ArrayList<Kitten> kittenArrayList) {
        Log.d("MyDebug","generateKittens START");
        Log.d("MyDebug","find recycler view");
        recyclerView = findViewById(R.id.kitten_list_view);
        Log.d("MyDebug","adapter");
        adapter = new KittenAdapter(kittenArrayList);
        Log.d("MyDebug","layoutManager");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        Log.d("MyDebug","setLayout");
        recyclerView.setLayoutManager(layoutManager);
        Log.d("MyDebug","setAdapter");
        recyclerView.setAdapter(adapter);
        Log.d("MyDebug","generateKittens FIN");
    }
}
