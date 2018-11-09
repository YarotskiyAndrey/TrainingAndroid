package com.example.kittenapp.model;

import com.google.gson.annotations.SerializedName;

public class KittenJson {
    @SerializedName("breeds")
    private String[] breeds;
    @SerializedName("categories")
    private String[] categories;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public KittenJson(String[] breeds, String[] categories, String id, String url) {
        this.breeds = breeds;
        this.categories = categories;
        this.id = id;
        this.url = url;
    }

    public String[] getBreeds() {
        return breeds;
    }

    public void setBreeds(String[] breeds) {
        this.breeds = breeds;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
