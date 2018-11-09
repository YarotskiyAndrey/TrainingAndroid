package com.example.kittenapp.network;

import com.example.kittenapp.model.Kitten;
import com.example.kittenapp.model.KittenJson;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KittenApi {
    @Headers({
            "Content-Type: application/json",
            "x-api-key: 7c3efa15-85fd-495b-8752-c235c29e6c7f"
    })
    @GET("search?format=json")
    Call<List<KittenJson>> getImageUrl(@Query("format") String format,
                                @Query("mime_types") String mime_types,
                                @Query("limit") int limit);
}
